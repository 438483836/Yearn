package com.yearn.life.zto.httpBillRuleUtil;

import com.yearn.life.utils.ToolUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;

/**
 * Created by Vincent on 2018-10-16
 */
@Service
public class HttpBillRuleService  implements ApplicationContextAware {

    private static Logger logger = LogManager.getLogger(HttpBillRuleService.class);

    private static ApplicationContext applicationContext;

    public static final LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private static String patternString = "";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        startService();
    }

    private void startService() {
        HttpBillRuleClient httpBillRuleClient = new HttpBillRuleClient();

        try {
            httpBillRuleClient.start();
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
                            httpBillRuleClient.sendImpl(msg);
                        } catch (Exception e) {
                            logger.error("send bill-rule request to zto Exception1[{}]",e.getMessage());
                        }
                    }
                } catch (InterruptedException e) {
                    logger.error("send bill-rule request to zto Exception2[{}]",e.getMessage());
                }
            }
        });

        thread.start();
        logger.info("HttpBillRuleService startService success-------------->");

    }

    public static void initCache(String billRule){
        patternString = billRule;
        logger.info("HttpBillRuleService initCache success billRule:{}",billRule);
    }

    public static boolean matchBarCode(String billCode)
    {
        boolean isMatch = Pattern.matches(patternString, billCode);
        return isMatch;
    }
}
