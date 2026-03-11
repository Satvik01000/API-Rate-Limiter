package com.PersonalProjects.TokenBucket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter {
    private final int capacity;
    private final int refillRate;
    private final Map<String, TokenBucket> userBuckets;

    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.userBuckets = new ConcurrentHashMap<>();
    }

    public boolean allowRequest(String key){
        userBuckets.putIfAbsent(key, new TokenBucket(capacity, refillRate));
        TokenBucket bucket = userBuckets.get(key);
        return bucket.tryConsume();
    }
}
