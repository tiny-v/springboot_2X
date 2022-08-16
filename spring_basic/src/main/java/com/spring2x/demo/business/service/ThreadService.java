package com.spring2x.demo.business.service;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 * @author tiny_v
 * @date 2022/3/18.
 */
public interface ThreadService {



    void asyncTask(int num);

}
