package com.exam.demo.configuration;  // Ensure the package name is correct

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow all endpoints
        registry.addMapping("/**")
                // Allow frontend origin (your Angular frontend on port 4200)
                .allowedOrigins("https://todo-app-angular-spring-boot.vercel.app")
                // Allow methods you want to use
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // Allow all headers
                .allowedHeaders("*")
                // Allow credentials (like cookies, if needed)
                .allowCredentials(true);
    }
}
