package com.tinyv.demo.global.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.tinyv.demo.global.properties.LocalCacheProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.caffeine.CaffeineCacheManager;
/**
 *
 * @author YMa69
 * @date 2019/11/19
 */
@EnableConfigurationProperties(LocalCacheProperties.class)
@Configuration
public class CaffeineConfig {

    @Autowired
    public LocalCacheProperties localCacheProperties;

    @Bean("cacheLoader")
    public LocalCacheLoader localCacheLoader(){
        return new LocalCacheLoader();
    }

    @Bean(name="cacheCaffeine")
    public Caffeine caffeine(){
        Caffeine caffeine = Caffeine.newBuilder();
        caffeine.initialCapacity(localCacheProperties.getInitialCapacity());
        caffeine.maximumSize(localCacheProperties.getMaximumSize());
        caffeine.expireAfterWrite(localCacheProperties.getExpireAfterWrite(), TimeUnit.SECONDS);
        return caffeine;
    }

    @Bean
    public CacheManager caffeineCacheManager(@Qualifier("cacheLoader") LocalCacheLoader localCacheLoader,
                                             @Qualifier("cacheCaffeine") Caffeine caffeine){
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCacheLoader(localCacheLoader);
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }


}
