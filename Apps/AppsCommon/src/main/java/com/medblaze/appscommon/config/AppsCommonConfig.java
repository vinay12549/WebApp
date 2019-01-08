package com.medblaze.appscommon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.medblaze.appscommon")
public class AppsCommonConfig {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

}
