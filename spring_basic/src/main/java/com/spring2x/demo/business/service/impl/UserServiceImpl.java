package com.spring2x.demo.business.service.impl;

import com.spring2x.core.utils.SHAEncodeUtil;
import com.spring2x.demo.business.bean.User;
import com.spring2x.demo.business.dao.UserDao;
import com.spring2x.demo.business.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;

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
     * @param userId
     * @return
     */
    @Override
    public HashMap getUserMapById(String userId) {
        if(StringUtils.isBlank(userId)){
            return null;
        }
        return userDao.getUserMapById(userId);
    }

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public int insertUser(User user) {
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
