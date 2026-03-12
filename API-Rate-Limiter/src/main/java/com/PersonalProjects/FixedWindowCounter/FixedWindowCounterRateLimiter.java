package com.PersonalProjects.FixedWindowCounter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowCounterRateLimiter {
    private final int windowSize;
    private final int maxRequest;
    private final Map<String, FixedWindowCounter> userBuckets;

    public FixedWindowCounterRateLimiter(int windowSize, int maxRequest) {
        this.windowSize = windowSize;
        this.maxRequest = maxRequest;
        this.userBuckets = new ConcurrentHashMap<>();
    }

    public boolean allowRequest(String key){
        userBuckets.putIfAbsent(key, new FixedWindowCounter(windowSize, maxRequest));
        FixedWindowCounter bucket = userBuckets.get(key);
        return bucket.tryConsume();
    }
}