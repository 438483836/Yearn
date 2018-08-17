package com.yearn.life.controller;

import com.yearn.life.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Vincent on 2018-08-15.
 */
@Controller
public class RegisteredController {

    private static Logger logger = LogManager.getLogger(RegisteredController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(path = "registered")
    public Object registered(){
        return "/registered";
    }

    @RequestMapping(path = "registeredMess")
    public Object registeredMess(HttpServletRequest request, HttpServletResponse response, Model model)throws IOException {
        String firstName = request.getParameter("form-first-name");
        String lastName = request.getParameter("form-last-name");
        String email = request.getParameter("form-email");
        String password = request.getParameter("password");
        logger.info("姓参数: {},名参数：{},邮箱：{}",firstName,lastName,email);
        Map<String, Object> result = userService.registeredData(firstName,lastName,email,password);
        if ("0000".equals(result.get("code"))){
            logger.info("注册成功");
            return "/login";
        }else {
            model.addAttribute("error",result.get("codeDesc"));
            response.setStatus(500);
            logger.debug("注册失败");
            return "/registered";
        }
    }
}
