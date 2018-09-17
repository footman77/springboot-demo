package com.example.demo.config;

import com.example.demo.controller.interceptor.OneInterceptor;
import com.example.demo.controller.interceptor.TwoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");
        registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**").addPathPatterns("/one/**");


    }

}
