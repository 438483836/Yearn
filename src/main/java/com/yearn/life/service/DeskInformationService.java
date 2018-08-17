package com.yearn.life.service;

import com.yearn.life.pojo.DeskInformation;

import java.util.List;

/**
 * Created by Vincent on 2018-08-15.
 */
public interface DeskInformationService {

    DeskInformation getByBarcode(String barcode);

    DeskInformation save(DeskInformation deskInformation);

    List<DeskInformation> findByData();

}
