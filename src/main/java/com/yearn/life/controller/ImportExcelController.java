package com.yearn.life.controller;

import com.yearn.life.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vincent on 2018-10-15
 */
@Controller
public class ImportExcelController {

    @Autowired
    private ImportService importService;

    @RequestMapping(path = "uploadExcel")
    public Object uploadExcel(){
        return "/uploadExcel";
    }

    @RequestMapping(path = "import", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importExcel(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        String result = importService.readExcelFile(file);
        map.put("message", result);
        return map;
    }
}
