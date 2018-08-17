package com.yearn.life.service;

import com.yearn.life.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Vincent on 2018-08-13.
 */
public interface UserService {

    List<User> findAll();

    User save(User user);

    Map<String, Object> loginVaData(String user, String pwd);

    Map<String, Object> registeredData(String firstName,String lastName,String email,String password);
}
