package com.toychat.prj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // URL 패턴에 따라 index.html로 리다이렉트
        registry.addViewController("/admin").setViewName("forward:/index.html");
    }
}
