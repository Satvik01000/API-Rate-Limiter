package com.PersonalProjects.SlidingWindowCounter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterRateLimiter {
  private final long windowSize;
  private final int maxRequests;
  private final Map<String, SlidingWindowCounter> userBuckets;

  public SlidingWindowCounterRateLimiter(long windowSize, int maxRequests) {
    this.windowSize = windowSize;
    this.maxRequests = maxRequests;
    userBuckets = new ConcurrentHashMap<>();
  }

  public boolean allowRequest(String key) {
    userBuckets.putIfAbsent(key, new SlidingWindowCounter(windowSize, maxRequests));
    SlidingWindowCounter bucket = userBuckets.get(key);
    return bucket.tryConsume();
  }
}
