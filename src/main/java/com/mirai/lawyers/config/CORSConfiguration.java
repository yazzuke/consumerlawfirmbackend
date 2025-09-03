package com.mirai.lawyers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;  // Añadir esta importación
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Añadir esta anotación
public class CORSConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173", "https://consumerlawfirmgroup.com")
                        .allowedMethods("GET", "POST", "OPTIONS") 
                        .allowedHeaders("*")
                        .allowCredentials(true); 
            }
        };
    }
}