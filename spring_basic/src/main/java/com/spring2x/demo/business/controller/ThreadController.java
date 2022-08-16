package com.spring2x.demo.business.controller;

import com.spring2x.demo.business.service.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tiny_v
 * @date 2022/3/18.
 */
@RestController
@RequestMapping("/thread")
public class ThreadController {

    public static final Logger logger = LoggerFactory.getLogger(ThreadController.class);

    @Autowired
    private ThreadService threadService;

    @GetMapping(value="/async")
    public void asyncTest(){
        for(int i=0; i<5; i++){
            threadService.asyncTask(i);
        }
        logger.info("== come into async test method ==");
    }

}
