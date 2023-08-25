package com.example.teamproject.config;


import com.example.teamproject.utility.PropertyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@PropertySource(value = "classpath:application.properties")
public class CorsConfig implements WebMvcConfigurer {

    private final PropertyUtil propertyUtil;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        final String ALLOWED_ORIGINS = propertyUtil.getProperty("allowed_origins");

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000/","http://15.165.11.253:3001","http://15.165.11.253:80",ALLOWED_ORIGINS)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders(HttpHeaders.AUTHORIZATION)
                .allowCredentials(true);
    }
}