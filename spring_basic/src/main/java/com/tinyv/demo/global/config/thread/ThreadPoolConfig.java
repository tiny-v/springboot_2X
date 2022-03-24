package com.tinyv.demo.global.config.thread;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: tiny_v
 * @Date: 2020/4/27
 */
@ConfigurationProperties(prefix = "thread.pool")
@Configuration
@Setter
public class ThreadPoolConfig {
    private static final String POOL_NAME = "core-pool";
    /**
     * 拒绝策略，当线程池满负载无法处理新提交的任务时，异步转同步，让调用者直接调用run方法
     */
    private static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER =
            new ThreadPoolExecutor.CallerRunsPolicy();
    /**
     * 核心线程数，一直存在于线程池，不会因为超过keepAliveTime被回收
     * 本应用大量的查询数据库/调用外部接口，属于IO密集型的应用，可适当增大线程数以提升性能
     */
    @Value("${corePoolSize:8}")
    private Integer corePoolSize;
    /**
     * 最大线程数，当任务队列满时，线程池会创建不多于maximumPoolSize数量的线程
     * 超过核心线程数的那部分线程受keepAliveTime限制可能会被回收
     */
    @Value("${maximumPoolSize:32}")
    private Integer maximumPoolSize;
    /**
     * 线程处于空闲时间超过该值时被回收，单位秒
     */
    @Value("${keepAliveTime:5}")
    private Long keepAliveTime;
    /**
     * 线程池内任务最大数量，该值设置过小时会影响性能
     * 受内存限制该值也不宜太大，否则会OOM
     */
    @Value("${maximumDequeSize:2048}")
    private Integer maximumDequeSize;

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new MonitorThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(maximumDequeSize),
                POOL_NAME,
                REJECTED_EXECUTION_HANDLER
        );
    }
}
