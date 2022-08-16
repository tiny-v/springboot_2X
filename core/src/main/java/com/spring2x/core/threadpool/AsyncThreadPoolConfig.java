package com.spring2x.core.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author mayue
 * @date 2022/3/18.
 * 使用自定义的线程池，替换@Async注解使用的默认线程池
 */
@Configuration
public class AsyncThreadPoolConfig implements AsyncConfigurer {

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    private final Logger logger = LoggerFactory.getLogger(AsyncThreadPoolConfig.class);

    @Override
    public Executor getAsyncExecutor() {
        return threadPoolExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }

    class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            logger.info("exception message:[{}]", throwable.getMessage());
            logger.info("method name:[{}]" , method.getName());
            for (Object param : objects) {
                logger.info("parameter value:[{}]", param);
            }
        }
    }

}
