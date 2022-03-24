package com.tinyv.demo.global.config.jmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author tiny_v
 * @date 2022/3/18.
 */
@Component
@ManagedResource(objectName = "com.tinyv.jmx:type=ThreadBean", description = "自定义线程池信息")
public class ThreadMBean {

    public static final Logger logger = LoggerFactory.getLogger(ThreadMBean.class);

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @ManagedOperation(description = "获取自定义线程池信息")
    public String selfThreadInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("PoolSize:").append(threadPoolExecutor.getPoolSize()).append(", ")
                .append("CorePoolSize:").append(threadPoolExecutor.getCorePoolSize()).append(", ")
                .append("TaskCount:").append(threadPoolExecutor.getTaskCount()).append(", ")
                .append("CompleteTaskCount:").append(threadPoolExecutor.getCompletedTaskCount()).append(", ");
        return sb.toString();
    }

}
