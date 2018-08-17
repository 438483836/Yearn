package com.yearn.life.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Vincent on 2018-08-15.
 */
@Component
@Order(value = 1)
public class MyCommandRunner implements CommandLineRunner {

    private static Logger logger = LogManager.getLogger(MyCommandRunner.class);

    @Value("${spring.web.loginUrl}")
    private String loginUrl;

    @Value("${spring.web.googlePath}")
    private String googlePath;

    @Override
    public void run(String... strings) throws Exception {
        String cmd = googlePath + " " + loginUrl;
        Runtime run = Runtime.getRuntime();
        try{
            logger.info("页面启动成功");
            run.exec(cmd);
        }catch (Exception e){
            logger.error("启动默认首页报错信息: ", e.getMessage());
        }
    }
}
