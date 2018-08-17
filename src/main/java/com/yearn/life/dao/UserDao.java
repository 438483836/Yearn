package com.yearn.life.dao;

import com.yearn.life.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vincent on 2018-08-13.
 */
public interface UserDao extends JpaRepository<User, Integer>{

    User findUserByUsernameAndPassword(String name,String password);

    User save(User user);

}
