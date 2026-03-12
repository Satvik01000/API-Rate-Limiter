package com.personalprojects.apiratelimiter.Config;

import com.personalprojects.apiratelimiter.RateLimiter.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TokenBucketRateLimiterInterceptor tokenBucketRateLimiterInterceptor;
    private final LeakyBucketRateLimiterInterceptor leakyBucketRateLimiterInterceptor;
    private final FixedWindowRateLimiterInterceptor fixedWindowRateLimiterInterceptor;
    private final SlidingWindowLogRateLimiterInterceptor slidingWindowLogRateLimiterInterceptor;
    private final SlidingWindowCounterRateLimiterInterceptor slidingWindowCounterRateLimiterInterceptor;

    public WebConfig(TokenBucketRateLimiterInterceptor tokenBucketRateLimiterInterceptor, LeakyBucketRateLimiterInterceptor leakyBucketRateLimiterInterceptor, FixedWindowRateLimiterInterceptor fixedWindowRateLimiterInterceptor, SlidingWindowLogRateLimiterInterceptor slidingWindowLogRateLimiterInterceptor, SlidingWindowCounterRateLimiterInterceptor slidingWindowCounterRateLimiterInterceptor) {
        this.tokenBucketRateLimiterInterceptor = tokenBucketRateLimiterInterceptor;
        this.leakyBucketRateLimiterInterceptor = leakyBucketRateLimiterInterceptor;
        this.fixedWindowRateLimiterInterceptor = fixedWindowRateLimiterInterceptor;
        this.slidingWindowLogRateLimiterInterceptor = slidingWindowLogRateLimiterInterceptor;
        this.slidingWindowCounterRateLimiterInterceptor = slidingWindowCounterRateLimiterInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(tokenBucketRateLimiterInterceptor)
                .addPathPatterns("/api/limited/tokenBucket");

        registry.addInterceptor(leakyBucketRateLimiterInterceptor)
                .addPathPatterns("/api/limited/leakyBucket");

        registry.addInterceptor(fixedWindowRateLimiterInterceptor)
                .addPathPatterns("/api/limited/fixedWindow");

        registry.addInterceptor(fixedWindowRateLimiterInterceptor)
                .addPathPatterns("/api/limited/fixedWindow");

        registry.addInterceptor(slidingWindowLogRateLimiterInterceptor)
                .addPathPatterns("/api/limited/slidingWindowLog");

        registry.addInterceptor(slidingWindowCounterRateLimiterInterceptor)
                .addPathPatterns("/api/limited/slidingWindowCounter");
    }
}
