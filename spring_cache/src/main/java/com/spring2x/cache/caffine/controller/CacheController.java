package com.spring2x.cache.caffine.controller;

import com.spring2x.cache.caffine.service.CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TinyV
 * @date 2019/11/20
 */
@Api(tags={"Cache_Caffine"}, produces = "application/json")
@RestController
@RequestMapping(value="/cache")
public class CacheController {

    private Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private CacheService cacheService;

    /**
     * Spring Cacheable Demo
     * @param cacheableTagId
     * @return
     */
    @ApiOperation(value="[@Cacheable]")
    @ApiImplicitParams(value={@ApiImplicitParam(name="cacheableTagId", value = "")})
    @GetMapping(value="/cacheable")
    public String cacheableDemo(String cacheableTagId){
        long startMill = System.currentTimeMillis();
        String result = cacheService.cacheableDemo(cacheableTagId);
        long endMill = System.currentTimeMillis();
        logger.info("===== Cacheable total cost:[{}]", (endMill-startMill));
        return result;
    }

    /**
     * Spring CachePut Demo
     * @param cachePutTagId
     * @return
     */
    @ApiOperation(value="[@CachePut]")
    @ApiImplicitParams(value={@ApiImplicitParam(name="cachePutTagId", value = "")})
    @GetMapping(value="/cachePut")
    public String cachePutDemo(String cachePutTagId){
        long startMill = System.currentTimeMillis();
        String result = cacheService.cachePutDemo(cachePutTagId);
        long endMill = System.currentTimeMillis();
        logger.info("===== CachePut total cost:[{}]", (endMill-startMill));
        return result;
    }

    /**
     * Spring CachePut Demo
     * @param cacheEvictTagId
     * @return
     */
    @ApiOperation(value="[@CacheEvict]")
    @ApiImplicitParams(value={@ApiImplicitParam(name="cacheEvictTagId", value = "")})
    @GetMapping(value="/cacheEvict")
    public String cacheEvictDemo(String cacheEvictTagId){
        long startMill = System.currentTimeMillis();
        String result = cacheService.cacheEvictDemo(cacheEvictTagId);
        long endMill = System.currentTimeMillis();
        logger.info("===== CacheEvict total cost:[{}]", (endMill-startMill));
        return result;
    }

    @ApiOperation(value="[showCacheRecordStats]")
    @GetMapping(value="/recordStats")
    public void showCacheRecordStats(){
        cacheService.showCacheRecordStats();
    }

}
