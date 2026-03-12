package com.personalprojects.apiratelimiter.RateLimiter;

import com.PersonalProjects.LeakyBucket.LeakyBucketRateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LeakyBucketRateLimiterInterceptor implements HandlerInterceptor {
    private final LeakyBucketRateLimiter rateLimiter = new LeakyBucketRateLimiter(10, 0.001);
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
