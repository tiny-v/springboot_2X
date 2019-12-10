package com.tinyv.demo.test.service;

import com.tinyv.demo.MyApps;
import com.tinyv.demo.business.service.CacheService;
import com.tinyv.demo.business.service.impl.CacheServiceImpl;
import com.tinyv.demo.test.tasks.CacheTestTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

/**
 * Created by YMa69 on 2019/11/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={MyApps.class, CacheServiceImpl.class})
public class CacheControllerTest {

    private static Logger logger = LoggerFactory.getLogger(CacheControllerTest.class);

    @Autowired
    private CacheService cacheService;

    @Test
    public void cacheableDemoTest() throws InterruptedException{

        CacheTestTask cacheableThread = new CacheTestTask(cacheService, "cacheable","Cacheable");
        FutureTask<String> cacheableTask = new FutureTask(cacheableThread);

        CacheTestTask cachePutThread = new CacheTestTask(cacheService, "cacheable","CachePut");
        FutureTask<String> cachePutTask = new FutureTask(cachePutThread);

        CacheTestTask cacheEvictThread = new CacheTestTask(cacheService, "cacheable","CacheEvict");
        FutureTask<String> cacheEvictTask = new FutureTask(cacheEvictThread);

        BlockingQueue workQueue = new LinkedBlockingQueue<FutureTask>(64);
        ExecutorService executorService = new ThreadPoolExecutor(5, 64, 10, TimeUnit.SECONDS, workQueue);

        executorService.submit(cacheableTask);
        executorService.submit(cachePutTask);
        executorService.submit(cacheEvictTask);


        Thread.sleep(1000000);
    }

}
