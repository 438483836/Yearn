package com.yearn.life.zto.httpPortSyncUtil;

import com.yearn.life.pojo.SortingSchemeConfig;
import com.yearn.life.utils.ToolUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Vincent on 2018-11-05
 */
public class HttpPortSyncService implements ApplicationContextAware {

    private static Logger logger = LogManager.getLogger(HttpPortSyncService.class);

    private static ApplicationContext applicationContext;

    public static final LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>();

    private static List<SortingSchemeConfig> sortingSchemeConfigList = new ArrayList<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        startService();
    }


    public static List<SortingSchemeConfig> getAllSortingSchemeConfig(){
        return sortingSchemeConfigList;
    }

    private void startService() {
        HttpPortSyncClient httpPortSyncClient = new HttpPortSyncClient();
        try {
            httpPortSyncClient.start();
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
                            httpPortSyncClient.sendImpl(msg);
                        } catch (Exception e) {
                            logger.error("send port-sync request to zto Exception1[{}]",e.getMessage());
                        }
                    }
                } catch (InterruptedException e) {
                    logger.error("send port-sync request to zto Exception2[{}]",e.getMessage());
                }
            }
        });

        thread.start();
        logger.info("HttpPortSyncService startService success-------------->");
    }

    public static void initCache(List<SortingSchemeConfig> sortingSchemeConfigs){
        sortingSchemeConfigList.addAll(sortingSchemeConfigs);
    }

    public static SortingSchemeConfig getSortingSchemeConfigBySortPortCode(String sortPortCode){
        for (SortingSchemeConfig sortingSchemeConfig:sortingSchemeConfigList){
            if (ToolUtil.equals(sortingSchemeConfig.getSortPortCode(),sortPortCode)){
                return sortingSchemeConfig;
            }
        }
        return null;
    }

    public static SortingSchemeConfig getSortingSchemeConfigBySortPortCodes(List<String> sortPortCodes){
        for (SortingSchemeConfig sortingSchemeConfig:sortingSchemeConfigList){
            for (String chute:sortPortCodes){
                if (ToolUtil.equals(sortingSchemeConfig.getSortPortCode(),chute)){
                    return sortingSchemeConfig;
                }
            }
        }
        return null;
    }

    public static List<String> getSortPortCodesBySortCode(String sortCode){
        List<String> sortPortCodeList = new ArrayList<>();
        for (SortingSchemeConfig sortingSchemeConfig:sortingSchemeConfigList){
            if (ToolUtil.equals(sortingSchemeConfig.getSortCode(),sortCode)){
                sortPortCodeList.add(sortingSchemeConfig.getSortPortCode());
            }
        }
        return sortPortCodeList;
    }
}
