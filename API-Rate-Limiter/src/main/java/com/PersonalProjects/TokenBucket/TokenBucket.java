package com.PersonalProjects.TokenBucket;

public class TokenBucket {
  private final int maximumCapacity;
  private final int refillRate;

  private double currentTokens;
  private long lastRefillTimestamp;

  public TokenBucket(int maximumCapacity, int refillRate) {
    this.maximumCapacity = maximumCapacity;
    this.refillRate = refillRate;
    this.currentTokens = maximumCapacity;
    this.lastRefillTimestamp = System.currentTimeMillis();
  }

  public synchronized boolean tryConsume() {
    refill();
    if (currentTokens >= 1.0) {
      currentTokens -= 1.0;
      return true;
    }
    return false;
  }

  private void refill() {
    long now = System.currentTimeMillis();
    long timeSpent = now - lastRefillTimestamp;
    currentTokens = currentTokens + (double) (timeSpent * refillRate) / 1000;
    currentTokens = Math.min(currentTokens, maximumCapacity);
    lastRefillTimestamp = now;
  }
}
