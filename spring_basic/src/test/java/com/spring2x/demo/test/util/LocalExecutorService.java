package com.spring2x.demo.test.util;

import java.util.concurrent.*;

/**
 * Created by TinyV on 2019/11/22.
 */
public class LocalExecutorService {

    private volatile static ExecutorService executorService = null;

    private static int blockQueueSize = 128;
    private static int corePoolSize = 5;
    private static int maxPoolSize = 20;
    private static int aliveTime = 10;
    private static int handlerNum = 3;

    private LocalExecutorService(){}

    public static ExecutorService getInstance(){
        if(executorService == null){
            init();
        }
        return executorService;
    }

    private static void init(){
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(blockQueueSize);
        RejectedExecutionHandler rejectedExecutionHandler = getHandler(handlerNum);
        executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize, aliveTime, TimeUnit.SECONDS, linkedBlockingQueue, rejectedExecutionHandler);
    }

    private static RejectedExecutionHandler getHandler(int handlerNum){
        if(handlerNum < 0 || handlerNum > 3){
            handlerNum = 0;
        }
        RejectedExecutionHandler rejectedExecutionHandler = null;
        switch(handlerNum){
            case 0:
                rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
                break;
            case 1:
                rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();
                break;
            case 2:
                rejectedExecutionHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
                break;
            case 3:
                rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
                break;
            default:
                rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
                break;
        }
        return rejectedExecutionHandler;
    }


}
