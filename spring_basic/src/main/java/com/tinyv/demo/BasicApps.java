package com.tinyv.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author TinyV
 * @date 2019/11/8.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.tinyv.demo")
@EnableCaching
@EnableAsync
@EnableScheduling
@EnableRetry
@MapperScan("com.tinyv.demo.business.dao")
public class BasicApps {

    public static final Logger logger = LoggerFactory.getLogger(BasicApps.class);

    public static void main(String[] args) {
        ApplicationContext ctx =  SpringApplication.run(BasicApps.class,args);
        String[] beanNames =  ctx.getBeanDefinitionNames();
        logger.info("bean个数：[{}]"+beanNames.length);
    }

   /* @Configuration
    @Profile("test")
    @ComponentScan(lazyInit = true)
    static class LocalConfig {
        static{
            logger.info("================= lazy init =====================");
        }
    }*/


}
