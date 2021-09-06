package com.tinyv.demo.test.service;

import com.tinyv.demo.BasicApps;
import com.tinyv.demo.business.service.TransactionService;
import com.tinyv.demo.business.service.impl.CacheServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tiny_v
 * @date 2021/9/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BasicApps.class, CacheServiceImpl.class})
public class TransactionServiceTest {


    private static Logger logger = LoggerFactory.getLogger(ProgrammerControllerTest.class);

    @Autowired
    private TransactionService transactionService;

    @Test
    public void parentTest(){
        transactionService.parent();
    }

    @Test
    public void scenarioBTest(){
        transactionService.scenario();
    }

    @Test
    public void catchScenarioTest(){
        transactionService.catchScenario();
    }

}
