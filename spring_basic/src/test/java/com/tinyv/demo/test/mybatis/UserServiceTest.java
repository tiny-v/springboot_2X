package com.tinyv.demo.test.mybatis;

import com.tinyv.demo.BasicApps;
import com.tinyv.demo.business.bean.User;
import com.tinyv.demo.business.service.UserService;
import com.tinyv.demo.business.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;

/**
 * @author tiny_v
 * @date 2021/8/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BasicApps.class, UserServiceImpl.class})
public class UserServiceTest {

    private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    public UserService userService;

    @Test
    public void testInsertUser(){
        User user = User.builder()
                .username("TYL")
                .nickname("SchoolMaster")
                .password("123456")
                .description("classical")
                .type(User.UserType.SINGER)
                .email("tyl@163.com")
                .telephone("0321-89877632")
                .build();
        int count = userService.insertUser(user);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testGetUserById(){
        User user = userService.getUserById("1");
        Assert.assertEquals(User.UserType.SINGER, user.getType());
    }

    @Test
    public void getGetUserMapById(){
        HashMap<String, User> map = userService.getUserMapById("1");
        Assert.assertEquals(1, map.size());
    }
}
