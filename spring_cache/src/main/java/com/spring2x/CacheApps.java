package com.spring2x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tiny_v
 * @date 2022/8/16.
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.spring2x")
public class CacheApps {

    public static void main(String[] args) {
        SpringApplication.run(CacheApps.class, args);
    }

}
