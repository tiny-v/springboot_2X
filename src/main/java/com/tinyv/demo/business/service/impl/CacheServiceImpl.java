package com.tinyv.demo.business.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.tinyv.demo.business.service.CacheService;
import com.tinyv.demo.global.constant.GlobalConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author YMa69
 * @date 2019/11/20
 */
@Service
public class CacheServiceImpl implements CacheService{

    private static Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    private static final long SLEEP_TIME = 5*1000;

    @Autowired
    private Caffeine cacheCaffeine;

    /**
     * 注解：@Cacheable Demo
     * 描述: 下次利用同样的参数来执行该方法时可以直接从缓存中获取结果，而不需要再次执行该方法
     * 属性：key | value | condition
     * @param cacheableTagId
     */
    @Cacheable(value="cache", cacheManager = GlobalConstants.CACHE_MANAGER_CAFFEINE_1)
    @Override
    public String cacheableDemo(String cacheableTagId) {
        logger.info("====== step into method[cacheableDemo] ======");
        try{
            Thread.sleep(SLEEP_TIME);
        }catch (InterruptedException e){
            logger.info("method[cacheableDemo]:"+e.getMessage());
        }
        return cacheableTagId+System.currentTimeMillis();
    }

    /**
     * 注解：@CachePut Demo
     * 描述: 在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中
     * 属性：key | value | condition
     * @param cachePutTagId
     */
    @CachePut(value="cache", cacheManager = GlobalConstants.CACHE_MANAGER_CAFFEINE_1)
    @Override
    public String cachePutDemo(String cachePutTagId) {
        logger.info("====== step into method[cachePutDemo] ======");
        try{
            Thread.sleep(SLEEP_TIME);
        }catch (InterruptedException e){
            logger.info("method[cachePutDemo]:"+e.getMessage());
        }
        return cachePutTagId+System.currentTimeMillis();
    }

    /**
     * 注解：@CacheEvict Demo
     * 描述: 用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作
     * 属性：key | value | condition | allEntries(是否清除所有entry, default:false) | beforeInvocation(是否在方法执行前就清除缓存, default:false)
     * @param cacheEvictTagId
     */
    @CacheEvict(value="cache", cacheManager = GlobalConstants.CACHE_MANAGER_CAFFEINE_1)
    @Override
    public String cacheEvictDemo(String cacheEvictTagId) {
        logger.info("====== step into method[cacheEvictDemo] ======");
        try{
            Thread.sleep(SLEEP_TIME);
        }catch (InterruptedException e){
            logger.info("method[cacheEvictDemo]:"+e.getMessage());
        }
        return cacheEvictTagId+System.currentTimeMillis();
    }

    @Override
    public void showCacheRecordStats() {
        Cache cache = cacheCaffeine.build();
        CacheStats cacheStats = cache.stats();
        logger.info("averageLoadPenalty:"+cacheStats.averageLoadPenalty());
        logger.info("hitCount:"+cacheStats.hitCount());
        logger.info("hitRate:"+cacheStats.hitRate());
        logger.info("evictionCount:"+cacheStats.evictionCount());
    }

}
