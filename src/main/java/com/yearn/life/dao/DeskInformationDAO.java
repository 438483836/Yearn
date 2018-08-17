package com.yearn.life.dao;


import com.yearn.life.pojo.DeskInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Vincent on 2018-06-25.
 */
public interface DeskInformationDAO extends JpaRepository<DeskInformation,Integer> {

     DeskInformation findByBarcode(@Param("barcode") String barcode);

     DeskInformation save(DeskInformation deskInformation);
}
