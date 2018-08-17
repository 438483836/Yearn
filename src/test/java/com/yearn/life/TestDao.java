package com.yearn.life;

import com.yearn.life.dao.DeskInformationDAO;
import com.yearn.life.pojo.DeskInformation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by Vincent on 2018-08-16.
 */
@SpringBootTest(classes = YearnApplication.class)
public class TestDao {

    @Autowired
    private DeskInformationDAO deskInformationDAO;

    @Test
    public void test(){
        DeskInformation d = deskInformationDAO.findByBarcode("6921734976505");
        System.out.println(d);
    }
}
