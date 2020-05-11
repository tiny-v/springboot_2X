package com.tinyv.demo.business.dao;

import com.tinyv.demo.business.bean.User;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TinyV
 * @date 2019/12/10
 */
@Repository
public interface UserDao {

    /**
     * 插入数据进入User table
     * @param user
     */
    void insertUser(User user);

    /**
     * 根据username, 查询user
     * @param username
     * @return
     */
    User getUser(String username);

}
