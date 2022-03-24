package com.tinyv.demo.business.service.impl;

import com.tinyv.demo.business.service.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author tiny_v
 * @date 2022/3/18.
 */
@Service
public class ThreadServiceImpl implements ThreadService {

    private static final Logger logger = LoggerFactory.getLogger(ThreadServiceImpl.class);


    @Override
    @Async
    public void asyncTask(int num){
        try{
            Thread.sleep(5*1000);
            logger.info("the num is: [{}]", num);
        }catch (InterruptedException e){
            logger.error("interrupt exception: ", e);
        }
        //return new AsyncResult(num);
    }
}
