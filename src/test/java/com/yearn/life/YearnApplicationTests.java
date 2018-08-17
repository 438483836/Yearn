package com.yearn.life;

import com.yearn.life.pojo.DeskInformation;
import com.yearn.life.service.DeskInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YearnApplicationTests {

	@Autowired
	private DeskInformationService deskInformationService;

	@Test
	public void contextLoads() {
		List<DeskInformation> data = deskInformationService.findByData();

		for (DeskInformation deskInformation : data){
			System.out.println(deskInformation);
		}
	}

}
