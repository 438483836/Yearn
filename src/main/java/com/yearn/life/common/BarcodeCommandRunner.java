package com.yearn.life.common;

import com.yearn.life.pojo.DeskInformation;
import com.yearn.life.service.DeskInformationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vincent on 2018-08-17.
 */
@Component
@Order(value = 3)
public class BarcodeCommandRunner implements CommandLineRunner {

    private static Logger logger = LogManager.getLogger(BarcodeCommandRunner.class);

    @Autowired
    private DeskInformationService deskInformationService;

    @Override
    public void run(String... strings) throws Exception {
        logger.info("=====【数据开始加载】=====");
        List<DeskInformation> data = deskInformationService.findByData();
        if (data != null){
            logger.info("=====【数据加载完成】=====");
        }
    }
}
