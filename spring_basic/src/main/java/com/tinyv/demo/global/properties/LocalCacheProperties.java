package com.tinyv.demo.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author TinyV
 * @date 2019/11/19
 */
@ConfigurationProperties(prefix="spring.cache.caffeine")
public class LocalCacheProperties {

    /** 初始缓存空间大小 */
    private int initialCapacity;
    /** 缓存的最大条数 */
    private long maximumSize;
    /** 缓存的最大权重 */
    private long maximumWeight;
    /** 最后一次写入或访问后经过固定的时间过期 */
    private long expireAfterAccess;
    /** 最后一次写入后经过固定的时间过期 */
    private long expireAfterWrite;
    /** 写入后经过固定的时间刷新缓存 */
    private long refreshAfterWrite;
    /** key为弱引用 */
    private boolean weakKeys;
    /** value为弱引用 */
    private boolean weakValues;
    /** value 为软引用 */
    private boolean softValues;
    /** 统计功能 */
    private boolean recordStats;

    public int getInitialCapacity() {
        return initialCapacity;
    }

    public void setInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    public long getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(long maximumSize) {
        this.maximumSize = maximumSize;
    }

    public long getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(long maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    public long getExpireAfterAccess() {
        return expireAfterAccess;
    }

    public void setExpireAfterAccess(long expireAfterAccess) {
        this.expireAfterAccess = expireAfterAccess;
    }

    public long getExpireAfterWrite() {
        return expireAfterWrite;
    }

    public void setExpireAfterWrite(long expireAfterWrite) {
        this.expireAfterWrite = expireAfterWrite;
    }

    public long getRefreshAfterWrite() {
        return refreshAfterWrite;
    }

    public void setRefreshAfterWrite(long refreshAfterWrite) {
        this.refreshAfterWrite = refreshAfterWrite;
    }

    public boolean isWeakKeys() {
        return weakKeys;
    }

    public void setWeakKeys(boolean weakKeys) {
        this.weakKeys = weakKeys;
    }

    public boolean isWeakValues() {
        return weakValues;
    }

    public void setWeakValues(boolean weakValues) {
        this.weakValues = weakValues;
    }

    public boolean isSoftValues() {
        return softValues;
    }

    public void setSoftValues(boolean softValues) {
        this.softValues = softValues;
    }

    public boolean isRecordStats() {
        return recordStats;
    }

    public void setRecordStats(boolean recordStats) {
        this.recordStats = recordStats;
    }
}
