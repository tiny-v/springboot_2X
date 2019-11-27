package com.tinyv.demo.business.util;

import java.util.concurrent.*;

/**
 * Created by YMa69 on 2019/11/22.
 */
public class LocalExecutorService {

    private int blockQueueSize = 128;
    private int corePoolSize = 5;
    private int maxPoolSize = 20;
    private int aliveTime = 10;
    private LinkedBlockingQueue linkedBlockingQueue;

    public LocalExecutorService(){}

    public LocalExecutorService(int blockQueueSize, int corePoolSize, int maxPoolSize, int aliveTime) {
        this.blockQueueSize = blockQueueSize;
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.aliveTime = aliveTime;
    }

    public ExecutorService getExecutorService(){
        linkedBlockingQueue = new LinkedBlockingQueue(blockQueueSize);
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize, aliveTime, TimeUnit.SECONDS, synchronousQueue,getHandler(3));
        return executorService;
    }

    public RejectedExecutionHandler getHandler(int handlerNum){
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
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
