package com.tinyv.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TinyV
 * @date 2020/4/14
 */
@RestController
@RequestMapping(value="/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping(value = "/insert")
    public void insert(String location){
        redisTemplate.opsForValue().set("location", location);
    }
}
