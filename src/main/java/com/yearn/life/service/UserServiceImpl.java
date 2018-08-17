package com.yearn.life.service;

import com.yearn.life.dao.UserDao;
import com.yearn.life.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vincent on 2018-08-14.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        List<User> list = this.userDao.findAll();
        return list;
    }

    @Override
    public User save(User user) {
        User u = userDao.save(user);
        return u;
    }


    @Override
    public Map<String, Object> loginVaData(String userName, String pwd) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = userDao.findUserByUsernameAndPassword(userName,pwd);
        result.put("code", "0001");
        result.put("codeDesc", "用户不存在");
        result.put("data", false);
        if (user != null){
            String userPwdDB = user.getPassword();
            if (pwd.equals(userPwdDB)){
                result.put("data", true);
                result.put("code", "0000");
                result.put("codeDesc", "成功");
                logger.info("Login Success");
            }else {
                result.put("code", "0002");
                result.put("codeDesc", "密码错误");
                logger.error("Login Error");
            }
        }

        return result;
    }

    @Override
    public Map<String, Object> registeredData(String firstName, String lastName, String email, String password) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName) || StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            result.put("code","0002");
            result.put("codeDesc","用户或者邮箱不能为空！！");
        }else {
            result.put("data",true);
            result.put("code","0000");
            result.put("codeDesc","注册成功");
            User u = new User();
            u.setFirst_name(firstName);
            u.setLast_name(lastName);
            u.setEmail(email);
            u.setPassword(password);
            u.setUsername(firstName + lastName);
            u.setCreate_time(new Date());
            userDao.save(u);
        }
        return result;
    }

}
