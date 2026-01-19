package com.springdev.rentalApp.config;

import com.springdev.rentalApp.config.interceptors.ExecutionTimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Configuration to register Interceptors.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ExecutionTimeInterceptor executionTimeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Register the interceptor and specify the path patterns it should apply to
        registry.addInterceptor(executionTimeInterceptor).addPathPatterns("/**");
    }
}
