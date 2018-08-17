package com.yearn.life.service;

import com.yearn.life.dao.ZtoBackMessDAO;
import com.yearn.life.pojo.ZtoResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vincent on 2018-08-16.
 */
@Service("ztoBackMessService")
public class ZtoBackMessServiceImpl implements ZtoBackMessService{

    @Autowired
    private ZtoBackMessDAO ztoBackMessDAO;

    @Override
    public ZtoResponseTO save(ZtoResponseTO ztoResponseTO) {
        return ztoBackMessDAO.save(ztoResponseTO);
    }
}
