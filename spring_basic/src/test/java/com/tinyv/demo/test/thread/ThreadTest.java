package com.tinyv.demo.test.thread;

import com.tinyv.demo.business.service.ThreadService;
import com.tinyv.demo.test.BasicTestApp;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tiny_v
 * @date 2022/3/18.
 */
public class ThreadTest extends BasicTestApp {

    @Autowired
    private ThreadService threadService;

    @Test
    public void asyncTaskTest(){
        for(int i=0; i<10; i++){
            threadService.asyncTask(i);
        }
    }

}
