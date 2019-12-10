package com.tinyv.demo.test.service;

import com.tinyv.demo.MyApps;
import com.tinyv.demo.business.bean.Programmer;
import com.tinyv.demo.business.service.ProgrammerService;
import com.tinyv.demo.business.service.impl.CacheServiceImpl;
import com.tinyv.demo.test.tasks.MySqlTestTask;
import com.tinyv.demo.test.util.LocalExecutorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by YMa69 on 2019/11/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={MyApps.class, CacheServiceImpl.class})
public class ProgrammerControllerTest {

    private static Logger logger = LoggerFactory.getLogger(ProgrammerControllerTest.class);

    @Autowired
    private ProgrammerService programmerService;

    @Test
    public void test1() throws InterruptedException{

        Programmer programmer = null;
        ArrayList<Callable<String>> list = new ArrayList<>();
        for(int i=0; i<5000; i++){
            programmer = new Programmer();
            programmer.setNickname("hello"+i);
            programmer.setDescription("i'm hello"+i);
            MySqlTestTask mySqlTestTask = new MySqlTestTask(programmerService, "INSERT", programmer);
            list.add(mySqlTestTask);
        }
        ExecutorService executorService = LocalExecutorService.getInstance();
        List<Future<String>> futureList = executorService.invokeAll(list);
        logger.info("future list size:"+futureList.size());
    }



}
