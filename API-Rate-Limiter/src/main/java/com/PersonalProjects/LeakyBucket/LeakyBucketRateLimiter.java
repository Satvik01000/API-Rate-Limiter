package com.PersonalProjects.LeakyBucket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeakyBucketRateLimiter {
    private final long capacity;
    private final double leakRate;
    private final Map<String, LeakyBucket> userBuckets;


    public LeakyBucketRateLimiter(long capacity, double leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        userBuckets = new ConcurrentHashMap<>();
    }

    public boolean allowRequest(String key){
        userBuckets.putIfAbsent(key, new LeakyBucket(capacity, leakRate));
        LeakyBucket bucket = userBuckets.get(key);
        return bucket.tryConsume();
    }
}
