package com.spring2x.demo.test.service;

import com.spring2x.BasicApps;
import com.spring2x.demo.test.tasks.MySqlTestTask;
import com.spring2x.demo.business.bean.Programmer;
import com.spring2x.demo.business.dao.ProgrammerDao;
import com.spring2x.demo.business.service.ProgrammerService;
import com.spring2x.demo.test.util.LocalExecutorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by TinyV on 2019/11/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BasicApps.class})
public class ProgrammerControllerTest{

    private static Logger logger = LoggerFactory.getLogger(ProgrammerControllerTest.class);

    @Autowired
    private ProgrammerService programmerService;
    @MockBean
    private ProgrammerDao programmerDao;

    @Test
    public void test1() throws InterruptedException{
        //
        when(programmerDao.insertProgrammer(any())).thenReturn(1);
        //
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
