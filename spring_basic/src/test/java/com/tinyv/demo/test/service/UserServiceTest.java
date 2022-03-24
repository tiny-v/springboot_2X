package com.tinyv.demo.test.service;

import com.tinyv.demo.BasicApps;
import com.tinyv.demo.business.bean.User;
import com.tinyv.demo.business.dao.UserDao;
import com.tinyv.demo.business.service.UserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @author tiny_v
 * @date 2021/8/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BasicApps.class})
public class UserServiceTest {

    private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    public UserService userService;

    @MockBean
    private UserDao userDao;

    @Test
    public void testInsertUser(){
        when(userDao.insertUser(any())).thenReturn(1);
        //
        User user = buildUser();
        int count = userService.insertUser(user);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testGetUserById(){
        when(userDao.getUserById(any())).thenReturn(buildUser());
        //
        User user = userService.getUserById("1");
        Assert.assertEquals(User.UserType.SINGER, user.getType());
    }

    @Test
    public void getGetUserMapById(){
        HashMap mockMap = new HashMap<String, User>();
        mockMap.put("1", buildUser());
        when(userDao.getUserMapById(any())).thenReturn(mockMap);
        //
        HashMap<String, User> map = userService.getUserMapById("1");
        Assert.assertEquals(1, map.size());
    }

    private User buildUser(){
        return User.builder()
                .username("TYL")
                .nickname("SchoolMaster")
                .password("123456")
                .description("classical")
                .type(User.UserType.SINGER)
                .email("tyl@163.com")
                .telephone("0321-89877632")
                .build();
    }
}
