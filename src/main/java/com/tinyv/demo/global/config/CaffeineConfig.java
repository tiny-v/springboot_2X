package com.tinyv.demo.global.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.tinyv.demo.global.constant.ConstGlobal;
import com.tinyv.demo.global.config.properties.LocalCacheProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Primary;

/**
 *
 * @author TinyV
 * @date 2019/11/19
 */
@EnableConfigurationProperties(LocalCacheProperties.class)
@Configuration
public class CaffeineConfig {

    public static Logger logger = LoggerFactory.getLogger(CaffeineConfig.class);

    @Autowired
    public LocalCacheProperties localCacheProperties;

    @Bean("cacheLoader")
    public LocalCacheLoader localCacheLoader(){
        return new LocalCacheLoader();
    }

    @Bean(name= ConstGlobal.CACHE_CAFFEINE_1)
    @Primary
    public Caffeine caffeine1(){
        Caffeine caffeine = Caffeine.newBuilder()
                // 初始化大小
                .initialCapacity(localCacheProperties.getInitialCapacity())
                // 最大容量
                .maximumSize(localCacheProperties.getMaximumSize())
                // 写入后过期时间
                .expireAfterWrite(localCacheProperties.getExpireAfterWrite(), TimeUnit.MINUTES)
                // 写入后更新时间
                .refreshAfterWrite(localCacheProperties.getRefreshAfterWrite(), TimeUnit.MINUTES)
                // 记录状态
                .recordStats()
                //todo 重写该删除监听器
                .removalListener(
                        (String key, String value, RemovalCause cause)->{
                            logger.info("key:"+key+" was removed, caused by:"+cause);
                        }
                );
        return caffeine;
    }

    @Bean(name= ConstGlobal.CACHE_CAFFEINE_2)
    public Caffeine caffeine2(){
        Caffeine caffeine = Caffeine.newBuilder()
                // 初始化大小
                .initialCapacity(localCacheProperties.getInitialCapacity())
                // 最大权重
                .maximumWeight(localCacheProperties.getMaximumWeight())
                // 访问后过期时间
                .expireAfterAccess(localCacheProperties.getExpireAfterAccess(), TimeUnit.MINUTES)
                // 记录状态
                .recordStats()
                //todo 重写该删除监听器
                .removalListener(
                        (String key, String value, RemovalCause cause)->{
                            logger.info("key:"+key+" was removed, caused by:"+cause);
                        }
                );
        return caffeine;
    }

    @Bean(name= ConstGlobal.CACHE_MANAGER_CAFFEINE_1)
    @Primary
    public CacheManager caffeineCacheManager1(@Qualifier("cacheLoader") LocalCacheLoader localCacheLoader,
                                             @Qualifier(ConstGlobal.CACHE_CAFFEINE_1) Caffeine caffeine){
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCacheLoader(localCacheLoader);
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    @Bean(name= ConstGlobal.CACHE_MANAGER_CAFFEINE_2)
    public CacheManager caffeineCacheManager2(@Qualifier(ConstGlobal.CACHE_CAFFEINE_2) Caffeine caffeine){
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }


}
