package com.spring2x.demo.business.service;

import com.spring2x.demo.business.bean.User;

import java.util.HashMap;

/**
 *
 * @author TinyV
 * @date 2019/12/10
 */
public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    int insertUser(User user);


    /**
     * userId
     * @param userId
     * @return
     */
    User getUserById(String userId);


    /**
     * @param userId
     * @return
     */
    HashMap getUserMapById(String userId);

}
