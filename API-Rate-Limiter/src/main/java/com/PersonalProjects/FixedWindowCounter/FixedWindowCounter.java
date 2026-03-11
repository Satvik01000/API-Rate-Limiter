package com.PersonalProjects.FixedWindowCounter;

public class FixedWindowCounter {
    private final long windowSize;
    private final int maxRequests;
    private int counter;
    private long currentWindowStartTime;

    public FixedWindowCounter(long windowSize, int maxRequests){
        this.windowSize = windowSize;
        this.maxRequests = maxRequests;
        this.counter = 0;
        this.currentWindowStartTime=(System.currentTimeMillis()/windowSize)*windowSize;
    }

    public synchronized boolean tryConsume(){
        long now = System.currentTimeMillis();
        long startTime = (now/windowSize)*windowSize;
        if(startTime>currentWindowStartTime){
            currentWindowStartTime=startTime;
            counter=0;
        }
        if(counter>=maxRequests) return false;
        counter++;
        return true;
    }
}
