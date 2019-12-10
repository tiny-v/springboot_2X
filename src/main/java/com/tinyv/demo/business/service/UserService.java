package com.tinyv.demo.business.service;

import com.tinyv.demo.business.bean.User;

/**
 *
 * @author YMa69
 * @date 2019/12/10
 */
public interface UserService {

    /**
     * 通过username查询user
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * 用户注册
     * @param user
     */
    void register(User user);

}
