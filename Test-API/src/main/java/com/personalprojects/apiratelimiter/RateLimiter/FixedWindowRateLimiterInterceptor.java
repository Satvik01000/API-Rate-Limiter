package com.personalprojects.apiratelimiter.RateLimiter;

import com.PersonalProjects.FixedWindowCounter.FixedWindowCounterRateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class FixedWindowRateLimiterInterceptor implements HandlerInterceptor {
    private final FixedWindowCounterRateLimiter rateLimiter = new FixedWindowCounterRateLimiter(60000, 10);

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
