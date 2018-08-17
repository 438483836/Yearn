package com.yearn.life.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Vincent on 2018-08-14.
 */
@Controller
public class ErrorController {

    private static Logger logger = LogManager.getLogger(ErrorController.class);

    @RequestMapping(path = "error/404")
    public Object error404(){
        logger.error("系统找不到页面, 404！！");
        return "/error/404";
    }

    @RequestMapping(path = "error/500")
    public Object error500(){
        return "/error/500";
    }

}


