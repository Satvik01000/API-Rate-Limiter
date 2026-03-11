package com.personalprojects.apiratelimiter.Config;

import com.personalprojects.apiratelimiter.RateLimiter.FixedWindowRateLimiterInterceptor;
import com.personalprojects.apiratelimiter.RateLimiter.SlidingWindowLogRateLimiterInterceptor;
import com.personalprojects.apiratelimiter.RateLimiter.TokenBucketRateLimiterInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TokenBucketRateLimiterInterceptor tokenBucketRateLimiterInterceptor;
    private final FixedWindowRateLimiterInterceptor fixedWindowRateLimiterInterceptor;
    private final SlidingWindowLogRateLimiterInterceptor slidingWindowLogRateLimiterInterceptor;

    public WebConfig(TokenBucketRateLimiterInterceptor tokenBucketRateLimiterInterceptor, FixedWindowRateLimiterInterceptor fixedWindowRateLimiterInterceptor, SlidingWindowLogRateLimiterInterceptor slidingWindowLogRateLimiterInterceptor) {
        this.tokenBucketRateLimiterInterceptor = tokenBucketRateLimiterInterceptor;
        this.fixedWindowRateLimiterInterceptor = fixedWindowRateLimiterInterceptor;
        this.slidingWindowLogRateLimiterInterceptor = slidingWindowLogRateLimiterInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(tokenBucketRateLimiterInterceptor)
                .addPathPatterns("/api/limited/tokenBucket");

        registry.addInterceptor(fixedWindowRateLimiterInterceptor)
                .addPathPatterns("/api/limited/fixedWindow");

        registry.addInterceptor(fixedWindowRateLimiterInterceptor)
                .addPathPatterns("/api/limited/fixedWindow");

        registry.addInterceptor(slidingWindowLogRateLimiterInterceptor)
                .addPathPatterns("/api/limited/slidingWindowLog");
    }
}
