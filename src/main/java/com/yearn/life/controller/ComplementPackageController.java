package com.yearn.life.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vincent on 2018-11-06
 */
@Controller
public class ComplementPackageController {

    @RequestMapping(path = "complementPackage")
    public Object complementPackage(){
        return "/complement";
    }


}
