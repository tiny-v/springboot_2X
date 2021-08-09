package com.tinyv.demo.business.service.impl;

import com.tinyv.demo.business.bean.User;
import com.tinyv.demo.business.dao.UserDao;
import com.tinyv.demo.business.service.UserService;
import com.tinyv.demo.util.SHAEncodeUtil;
import org.apache.commons.lang3.StringUtils;
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
     * userId
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserById(String userId) {
        if(StringUtils.isBlank(userId)){
            return null;
        }
        return userDao.getUserById(userId);
    }

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public int register(User user) {
        if(user == null){
            return 0;
        }
        // 密码加密
        if(StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(SHAEncodeUtil.shaEncode(user.getPassword()));
        }
        return userDao.insertUser(user);
    }




}
