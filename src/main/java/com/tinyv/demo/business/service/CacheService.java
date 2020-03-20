package com.tinyv.demo.business.service;

/**
 *
 * @author TinyV
 * @date 2019/11/20
 */
public interface CacheService {

    /**
     * 注解：@Cacheable Demo
     * @param cacheableTagId
     * @return
     */
    String cacheableDemo(String cacheableTagId);


    /**
     * 注解：@CachePut Demo
     * @param cachePutTagId
     * @return
     */
    String cachePutDemo(String cachePutTagId);


    /**
     * 注解：@CacheEvict Demo
     * @param cacheEvictTagId
     * @return
     */
    String cacheEvictDemo(String cacheEvictTagId);

    /**
     * 打印 Record'Status'
     */
    void showCacheRecordStats();

}
