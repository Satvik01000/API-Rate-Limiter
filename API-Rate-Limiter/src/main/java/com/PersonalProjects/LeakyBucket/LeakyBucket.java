package com.PersonalProjects.LeakyBucket;

public class LeakyBucket {
  private final long capacity;
  private final double leakRate;
  private double currentRequests;
  private long lastLeakTimeStamp;

  public LeakyBucket(long capacity, double leakRate) {
    this.capacity = capacity;
    this.leakRate = leakRate;
    this.currentRequests = 0;
    this.lastLeakTimeStamp = System.currentTimeMillis();
  }

  public synchronized boolean tryConsume() {
    long now = System.currentTimeMillis();
    long timeElapsed = now - lastLeakTimeStamp;

    double tokensSpent = timeElapsed * leakRate;

    currentRequests = Math.max(0, currentRequests - tokensSpent);

    lastLeakTimeStamp = now;

    if (currentRequests + 1 > capacity) {
      return false;
    }

    currentRequests++;
    return true;
  }
}
