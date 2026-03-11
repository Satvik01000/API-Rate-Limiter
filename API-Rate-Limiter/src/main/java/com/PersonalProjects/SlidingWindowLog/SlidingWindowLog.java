package com.PersonalProjects.SlidingWindowLog;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLog {
    private final long windowSize;
    private final long maxTokens;
    private final Queue<Long> requestWindow;

    public SlidingWindowLog(long windowSize, long maxTokens) {
        this.windowSize = windowSize;
        this.maxTokens = maxTokens;
        requestWindow = new LinkedList<Long>();
    }

    public synchronized boolean tryConsume(){
        long now = System.currentTimeMillis();
        while(!requestWindow.isEmpty()) {
            if(now-requestWindow.peek()<windowSize) break;
            requestWindow.poll();
        }
        if(requestWindow.size()<maxTokens) {
            requestWindow.add(now);
            return true;
        }
        return false;
    }
}
