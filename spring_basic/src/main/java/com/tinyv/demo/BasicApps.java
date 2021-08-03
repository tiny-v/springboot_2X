package com.tinyv.demo;

import com.tinyv.demo.netty.StationClient;
import com.tinyv.demo.netty.StationServer;
import org.checkerframework.checker.units.qual.A;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author TinyV
 * @date 2019/11/8.
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@MapperScan("com.tinyv.demo.business.dao")
public class BasicApps {

    public static Logger logger = LoggerFactory.getLogger(BasicApps.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BasicApps.class, args);
        StationServer stationServer = (StationServer)context.getBeanFactory().getBean("stationServer");
        stationServer.initServerBootStrap();
    }

}
