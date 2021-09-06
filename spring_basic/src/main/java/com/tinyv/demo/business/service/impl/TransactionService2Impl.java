package com.tinyv.demo.business.service.impl;

import com.tinyv.demo.business.bean.User;
import com.tinyv.demo.business.dao.UserDao;
import com.tinyv.demo.business.service.TransactionService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tiny_v
 * @date 2021/9/6.
 */
@Service
public class TransactionService2Impl implements TransactionService2 {

    @Autowired
    private UserDao userDao;

    /**
     * 场景
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void scenario(){
        userDao.insertUser(buildUser("userA"));
        int a = 1/0; // 此处制造异常
        userDao.insertUser(buildUser("userB"));
    }

    private User buildUser(String userName){
        return User.builder()
                .username(userName)
                .password("Pwd")
                .nickname("nickName")
                .type(User.UserType.Singer)
                .isEnabled(Boolean.TRUE)
                .build();
    }

}
