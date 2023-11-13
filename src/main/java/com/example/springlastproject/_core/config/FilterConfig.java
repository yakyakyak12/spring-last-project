package com.example.springlastproject._core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springlastproject._core.filter.JwtAuthorizationFilter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<JwtAuthorizationFilter> jwtFilter() {
        FilterRegistrationBean<JwtAuthorizationFilter> bean = new FilterRegistrationBean<>(
                new JwtAuthorizationFilter());
        bean.addUrlPatterns("/board/*");
        bean.addUrlPatterns("/boardReply/*");
        bean.addUrlPatterns("/boardLike/*");
        bean.addUrlPatterns("/bookReply/*");
        bean.addUrlPatterns("/book/bookOfMine/*");
        bean.addUrlPatterns("/bookLike/*");
        bean.addUrlPatterns("/update/*");
        bean.addUrlPatterns("/user/*");
        bean.addUrlPatterns("/readingbook/*");
        bean.addUrlPatterns("/payment/*");
        bean.setOrder(0); // 낮은 번호부터 실행됨
        return bean;
    }
}