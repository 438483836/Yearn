package com.yearn.life.controller;

import com.yearn.life.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Vincent on 2018-08-14.
 */
@Controller
public class LoginController {

    private static Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(path = "login")
    public Object login(){
        return "/login";
    }

    @RequestMapping(path = "loginVaData")
    public Object loginVaData(String userName,String userPwd,Model model){
        logger.info("参数用户名：{},密码：{}",userName,userPwd);
        Map<String,Object> result = userService.loginVaData(userName,userPwd);
        if("0000".equals(result.get("code"))) {
            return "/home";
        }
        model.addAttribute("error", result.get("codeDesc"));
        return "/login";
    }



}
