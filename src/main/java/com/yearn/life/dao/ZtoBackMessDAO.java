package com.yearn.life.dao;

import com.yearn.life.pojo.ZtoResponseTO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vincent on 2018-08-16.
 */
public interface ZtoBackMessDAO extends JpaRepository<ZtoResponseTO,Integer> {

    ZtoResponseTO save(ZtoResponseTO ztoResponseTO);

}
