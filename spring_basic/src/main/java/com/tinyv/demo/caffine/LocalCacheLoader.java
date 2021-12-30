package com.tinyv.demo.caffine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author TinyV
 * @date 2019/11/19
 */
public class LocalCacheLoader implements CacheLoader {


    private static Logger logger = LoggerFactory.getLogger(LocalCacheLoader.class);

    @Override
    public Object load(Object o){
        logger.info("== step into load method ==");
        return null;
    }

    @Override
    public Object reload(Object key, Object oldValue){
        logger.info("== step into reload method ==");
        return oldValue;
    }
}
