package com.spring2x.demo.business.dao;

import com.spring2x.demo.business.bean.User;
import org.springframework.stereotype.Repository;
import java.util.HashMap;

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
    int insertUser(User user);

    /**
     * 根据username, 查询user
     * @param username
     * @return
     */
    User getUser(String username);


    /**
     * 通过Id查询用户
     * @param id
     * @return
     */
    User getUserById(String id);


    /**
     * 通过Id查询用户
     * @param id
     * @return
     */
    HashMap getUserMapById(String id);




}
