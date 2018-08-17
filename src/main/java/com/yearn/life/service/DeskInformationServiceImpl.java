package com.yearn.life.service;

import com.yearn.life.dao.DeskInformationDAO;
import com.yearn.life.pojo.DeskInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vincent on 2018-08-15.
 */
@Service("deskInformationService")
public class DeskInformationServiceImpl implements DeskInformationService{

    private static Logger logger = LogManager.getLogger(DeskInformationServiceImpl.class);

    @Autowired
    private DeskInformationDAO deskInformationDAO;

    @Override
    @Cacheable(value = "deskInformation")
    public DeskInformation getByBarcode(String barcode) {
        DeskInformation d = deskInformationDAO.findByBarcode(barcode);
        logger.info("把barcode为key：" + barcode + "数据做了缓存");
        return d;

    }

    @Override
    public DeskInformation save(DeskInformation deskInformation) {

        return deskInformationDAO.save(deskInformation);
    }

    @Override
    @Cacheable(value = "deskInformation")
    public List<DeskInformation> findByData() {

        List<DeskInformation> data = deskInformationDAO.findAll();

        return data;
    }

}
