package com.spring2x.cache.caffine.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.spring2x.core.template.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author TinyV
 * @date 2019/11/19
 */
@EnableConfigurationProperties(LocalCacheProperties.class)
@Configuration
public class CaffeineConfig {

    private static final Logger logger = LoggerFactory.getLogger(CaffeineConfig.class);

    @Autowired
    public LocalCacheProperties localCacheProperties;

    @Bean("cacheLoader")
    public LocalCacheLoader localCacheLoader(){
        return new LocalCacheLoader();
    }

    @Bean(name= GlobalConstant.CACHE_CAFFEINE_1)
    @Primary
    public Caffeine<String, Object> caffeine1(){
        return Caffeine.newBuilder()
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
                .removalListener(
                        (String key, Object value, RemovalCause cause)->
                                logger.info("key:[{}] was removed, cause by:[{}]", key, cause)
                );
    }

    @Bean(name= GlobalConstant.CACHE_CAFFEINE_2)
    public Caffeine<String, Object> caffeine2(){
        return Caffeine.newBuilder()
                // 初始化大小
                .initialCapacity(localCacheProperties.getInitialCapacity())
                // 最大权重
                .maximumWeight(localCacheProperties.getMaximumWeight())
                // 访问后过期时间
                .expireAfterAccess(localCacheProperties.getExpireAfterAccess(), TimeUnit.MINUTES)
                // 记录状态
                .recordStats()
                .removalListener(
                        (String key, Object value, RemovalCause cause)->
                                logger.info("key:{} was removed, cause by:{}", key, cause)
                );
    }

    @Bean(name= GlobalConstant.CACHE_MANAGER_CAFFEINE_1)
    @Primary
    public CacheManager caffeineCacheManager1(@Qualifier("cacheLoader") LocalCacheLoader localCacheLoader,
                                              @Qualifier(GlobalConstant.CACHE_CAFFEINE_1) Caffeine caffeine){
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCacheLoader(localCacheLoader);
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    @Bean(name= GlobalConstant.CACHE_MANAGER_CAFFEINE_2)
    public CacheManager caffeineCacheManager2(@Qualifier(GlobalConstant.CACHE_CAFFEINE_2) Caffeine caffeine){
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }


}
