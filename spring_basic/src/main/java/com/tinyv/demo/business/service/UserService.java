package com.tinyv.demo.business.service;

import com.tinyv.demo.business.bean.User;

/**
 *
 * @author TinyV
 * @date 2019/12/10
 */
public interface UserService {

    /**
     * userId
     * @param userId
     * @return
     */
    User getUserById(String userId);

    /**
     * 用户注册
     * @param user
     */
    int register(User user);

}
