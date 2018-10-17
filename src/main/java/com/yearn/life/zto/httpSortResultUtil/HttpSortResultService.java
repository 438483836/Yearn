package com.yearn.life.zto.httpSortResultUtil;

import com.yearn.life.utils.ToolUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Vincent on 2018-10-16
 */
@Service
public class HttpSortResultService implements ApplicationContextAware {

    private static Logger logger = LogManager.getLogger(HttpSortResultService.class);

    private static ApplicationContext applicationContext;

    public static final LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        startService();
    }

    private void startService() {
        HttpSortResultClient httpSortResultClient = new HttpSortResultClient();
        try {
            httpSortResultClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(() -> {
            while (true){
                try {
                    String msg = linkedBlockingQueue.take();
                    if (ToolUtil.isNotEmpty(msg)){
                        Thread.sleep(20);
                        try {
                            httpSortResultClient.sendImpl(msg);
                        } catch (Exception e) {
                            logger.error("send sort-result request to zto Exception1[{}]",e.getMessage());
                        }
                    }
                } catch (InterruptedException e) {
                    logger.error("send sort-result request to zto Exception2[{}]",e.getMessage());
                }
            }
        });

        thread.start();
        logger.info("HttpSortResultClientHandler startService success-------------->");
    }
}
