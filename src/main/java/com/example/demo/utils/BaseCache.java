package com.example.demo.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BaseCache {

    private Cache<String, Object> tenMinuteCache = CacheBuilder.newBuilder()
            // setup cache initial size
            .initialCapacity(10)
            // setup maximum size
            .maximumSize(100)
            // setup concurrency of 5
            .concurrencyLevel(5)
            // cache expire time, after 10 minutes of writing
            .expireAfterWrite(600, TimeUnit.SECONDS)
            // record use data of cache
            .recordStats()
            .build();

    private Cache<String, Object> oneHourCache = CacheBuilder.newBuilder()
            // setup cache initial size
            .initialCapacity(30)
            // setup maximum size
            .maximumSize(100)
            // setup concurrency of 5
            .concurrencyLevel(5)
            // cache expire time, after 60 minutes of writing
            .expireAfterWrite(3600, TimeUnit.SECONDS)
            // record use data of cache
            .recordStats()
            .build();

    public Cache<String, Object> getOneHourCache() {
        return oneHourCache;
    }

    public void setOneHourCache(Cache<String, Object> oneHourCache) {
        this.oneHourCache = oneHourCache;
    }

    public Cache<String, Object> getTenMinuteCache() {
        return tenMinuteCache;
    }

    public void setTenMinuteCache(Cache<String, Object> tenMinuteCache) {
        this.tenMinuteCache = tenMinuteCache;
    }
}
