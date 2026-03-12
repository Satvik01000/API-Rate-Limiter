package com.personalprojects.apiratelimiter.RateLimiter;

import com.PersonalProjects.SlidingWindowCounter.SlidingWindowCounterRateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SlidingWindowCounterRateLimiterInterceptor implements HandlerInterceptor {
    private final SlidingWindowCounterRateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(10000, 5);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();
        boolean isAllowed = rateLimiter.allowRequest(ipAddress);

        if(isAllowed) {
            return  true;
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("429 - Too Many Requests. Try again later.");
            return false;
        }
    }
}