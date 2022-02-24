package com.tinyv.demo.test.service;

import com.tinyv.demo.BasicApps;
import com.tinyv.demo.business.service.RetryService;
import com.tinyv.demo.business.service.impl.RetryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tiny_v
 * @date 2022/2/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BasicApps.class, RetryServiceImpl.class})
public class RetryServiceTest {

    @Autowired
    private RetryService retryService;

    @Test
    public void doBizTest(){
        retryService.doBiz();
        retryService.doBiz2();
    }

}
