package com.PersonalProjects.SlidingWindowLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLogRateLimiter {
    private final int windowSize;
    private final int maxTokens;
    private final Map<String, SlidingWindowLog> userBuckets;

    public SlidingWindowLogRateLimiter(int windowSize, int maxTokens) {
        this.windowSize = windowSize;
        this.maxTokens = maxTokens;
        userBuckets = new ConcurrentHashMap<>();
    }

    public boolean allowRequest(String key){
        userBuckets.putIfAbsent(key, new SlidingWindowLog(windowSize, maxTokens));
        SlidingWindowLog bucket = userBuckets.get(key);
        return bucket.tryConsume();
    }
}
