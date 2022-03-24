package com.tinyv.demo.global.config.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tiny_v
 * @date 2021/5/15.
 *
 * 重写ThreadPoolExecutor, 使其在执行任务时，打印监控日志
 */
public class MonitorThreadPoolExecutor extends ThreadPoolExecutor {

    private final Logger logger = LoggerFactory.getLogger(MonitorThreadPoolExecutor.class);

    // 任务开始时间
    private ConcurrentHashMap<String, Date> startTimes;

    // 线程池名称
    private String poolName;

    public MonitorThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, String poolName, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new MonitorThreadFactory(poolName), handler);
        this.startTimes = new ConcurrentHashMap<>();
        this.poolName = poolName;
    }

    /**
     * 任务执行之前，记录任务开始时间
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        startTimes.put(String.valueOf(r.hashCode()), new Date());
        super.beforeExecute(t, r);
    }

    /**
     * 任务执行之后，计算任务结束时间, 并输出线程池的其它信息
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
        Date finishDate = new Date();
        // 统计任务耗时、初始线程数、核心线程数、正在执行的任务数量、已完成任务数量、任务总数、队列里缓存的任务数量、池中存在的最大线程数、最大允许的线程数、线程空闲时间、线程池是否关闭、线程池是否终止
        logger.info("{} : Duration: {} ms, PoolSize: {}, CorePoolSize: {}, Active: {}, Completed: {}, Task: {}, Queue: {}, LargestPoolSize: {}, MaximumPoolSize: {},  KeepAliveTime: {}, isShutdown: {}, isTerminated: {}",
                this.poolName,
                finishDate.getTime() - startDate.getTime(),
                this.getPoolSize(),
                this.getCorePoolSize(),
                this.getActiveCount(),
                this.getCompletedTaskCount(),
                this.getTaskCount(),
                this.getQueue().size(),
                this.getLargestPoolSize(),
                this.getMaximumPoolSize(),
                this.getKeepAliveTime(TimeUnit.MILLISECONDS),
                this.isShutdown(),
                this.isTerminated()
        );
        super.afterExecute(r, t);
    }

    /**
     * 线程池延迟关闭时，统计线程池情况
     */
    @Override
    public void shutdown() {
        // 统计已执行任务、正在执行任务、未执行任务数量
        logger.info("{} is Going to shutdown. Executed tasks: {}, Running tasks: {}, Pending tasks: {}",
                this.poolName, this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
        super.shutdown();
    }

    /**
     * 线程池立即关闭时，统计线程池情况
     */
    @Override
    public List<Runnable> shutdownNow() {
        // 统计已执行任务、正在执行任务、未执行任务数量
        logger.info("{} Going to immediately shutdown. Executed tasks: {}, Running tasks: {}, Pending tasks: {}",
                this.poolName, this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
        return super.shutdownNow();
    }

    /**
     * 生成线程池所用的线程，只是改写了线程池默认的线程工厂，传入线程池名称
     */
    static class MonitorThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;

        /**
         * 初始化线程工厂
         *
         * @param poolName 线程池名称
         */
        MonitorThreadFactory(String poolName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = poolName + "-pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()){
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY){
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

}
