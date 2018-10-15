package com.yearn.life.service;

import com.yearn.life.dao.PersonDAO;
import com.yearn.life.utils.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Vincent on 2018-10-15
 */
@Service("importService")
public class ImportServiceImpl implements ImportService {

    @Autowired
    private PersonDAO personDAO;

    @Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<Map<String, Object>> personList = readExcel.getExcelInfo(file);
        for(Map<String, Object> person : personList){
            int ret = personDAO.insertPerson(person.get("name").toString(),person.get("sex").toString(),Integer.parseInt(person.get("age").toString()));
            if (ret == 0){
                result = "插入数据库失败";
            }
        }
        if (personList != null && !personList.isEmpty()){
            result = "上传成功";
        }else {
            result = "上传失败";
        }
        return result;
    }

}
