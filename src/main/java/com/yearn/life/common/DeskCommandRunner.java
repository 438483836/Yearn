package com.yearn.life.common;

import com.yearn.life.socket.client.ScanCodeSendStation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 上件台发送PLC线程
 * Created by Vincent on 2018-08-16.
 */
@Component
@Order(value = 2)
public class DeskCommandRunner implements CommandLineRunner {

    private static Logger logger = LogManager.getLogger(DeskCommandRunner.class);

    @Override
    public void run(String... strings) throws Exception {

        ScanCodeSendStation scanCodeSendStation = new ScanCodeSendStation();
        try {
            scanCodeSendStation.start();
            logger.info("上件台服务已启动......");
        } catch (Exception e) {
            logger.error("上件台服务启动异常:{}", e.getMessage());
        }

    }
}
