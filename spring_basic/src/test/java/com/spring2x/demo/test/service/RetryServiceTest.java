package com.spring2x.demo.test.service;

import com.spring2x.BasicApps;
import com.spring2x.demo.business.service.RetryService;
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
@SpringBootTest(classes={BasicApps.class})
public class RetryServiceTest{

    @Autowired
    private RetryService retryService;

    @Test
    public void doBizTest(){
        retryService.doBiz();
        retryService.doBiz2();
    }

    @Test
    public void doBiz3Test() throws Throwable {
        int result = retryService.doBiz3(5);
        System.out.println(result);
    }

}
