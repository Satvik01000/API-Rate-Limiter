package com.PersonalProjects.SlidingWindowCounter;

public class SlidingWindowCounter {
    private final long windowSize;
    private final int maxRequests;

    private int previousWindowCount;
    private int currentWindowCount;
    private long currentWindowStartTime;

    public SlidingWindowCounter(long windowSize, int maxRequests) {
        this.windowSize = windowSize;
        this.maxRequests = maxRequests;
        this.previousWindowCount = 0;
        this.currentWindowCount = 0;
        this.currentWindowStartTime = (System.currentTimeMillis() / windowSize) * windowSize;
    }

    public synchronized boolean tryConsume() {
        long now = System.currentTimeMillis();
        long currentBoxStart = (now / windowSize) * windowSize;

        long windowsPassed = (currentBoxStart - currentWindowStartTime) / windowSize;

        if (windowsPassed == 1) {
            previousWindowCount = currentWindowCount;
            currentWindowCount = 0;
            currentWindowStartTime = currentBoxStart;
        } else if (windowsPassed > 1) {
            previousWindowCount = 0;
            currentWindowCount = 0;
            currentWindowStartTime = currentBoxStart;
        }

        long timeElapsed = now - currentWindowStartTime;

        double weight = (double) (windowSize - timeElapsed) / windowSize;

        double estimatedRequests = currentWindowCount + (previousWindowCount * weight);

        if (estimatedRequests >= maxRequests) {
            return false;
        }

        currentWindowCount++;
        return true;
    }
}