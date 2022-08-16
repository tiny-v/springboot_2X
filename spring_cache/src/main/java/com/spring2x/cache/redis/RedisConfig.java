package com.spring2x.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author TinyV
 * @date 2020/4/14
 */
@EnableConfigurationProperties(RedisPoolProperties.class)
@Configuration
public class RedisConfig {

    @Autowired
    private RedisPoolProperties redisPoolProperties;

    private RedisConnectionFactory connectionFactory = null;

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){
        if(this.connectionFactory != null){
            return connectionFactory;
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 设置最肖空闲数
        poolConfig.setMinIdle(redisPoolProperties.getMinIdle());
        // 设置最大空闲数
        poolConfig.setMaxIdle(redisPoolProperties.getMaxIdle());
        // 设置最大等待毫秒数
        poolConfig.setMaxWaitMillis(redisPoolProperties.getMaxWait());
        // 设置最大连接数
        poolConfig.setMaxTotal(redisPoolProperties.getMaxActive());
        // 创建 Jedis 连接工厂
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        // 获取单机配置
        RedisStandaloneConfiguration rsc = jedisConnectionFactory.getStandaloneConfiguration();
        if(rsc!=null){
            rsc.setHostName(redisPoolProperties.getHost());
            rsc.setPort(redisPoolProperties.getPort());
            rsc.setPassword(redisPoolProperties.getPassword());
        }
        this.connectionFactory = jedisConnectionFactory;
        return connectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }

}
