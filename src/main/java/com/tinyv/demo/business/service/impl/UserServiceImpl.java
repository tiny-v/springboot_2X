package com.tinyv.demo.business.service.impl;

import com.tinyv.demo.business.bean.User;
import com.tinyv.demo.business.dao.UserDao;
import com.tinyv.demo.business.service.UserService;
import com.tinyv.demo.util.SHAEncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TinyV
 * @date 2019/12/10
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    /**
     * 通过username查询user
     *
     * @param username
     * @return
     */
    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public void register(User user) {
        String pwd = SHAEncodeUtil.shaEncode(user.getUsername());
        user.setPassword(pwd);
        userDao.insertUser(user);

    }

}
