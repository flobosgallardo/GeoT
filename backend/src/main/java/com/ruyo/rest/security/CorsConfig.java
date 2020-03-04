package com.ruyo.rest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
 
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowedOrigins("http://localhost:8080", "http://localhost").
                allowedMethods("GET", "POST", "OPTIONS", "PUT")
                .allowedHeaders("Authorization", "Cache-Control", "Content-Type", "X-Requested-With", "accept",
                        "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                .exposedHeaders("Authorization", "Cache-Control", "Content-Type", "X-Requested-With", "accept",
                        "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                .allowCredentials(true)
                .maxAge(3600);
    }


}