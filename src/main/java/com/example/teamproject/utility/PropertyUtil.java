package com.example.teamproject.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
@PropertySource(value = {
        "classpath:application.properties",
        "classpath:naver.properties",
        "classpath:kakao.properties",
        "classpath:redis.properties"
})
public class PropertyUtil {

    final private Environment environment;

    public String getProperty(String key) {
        return environment.getProperty(key);
    }
}