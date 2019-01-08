package com.medblaze.tenant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@ComponentScan(basePackages = "com.medblaze.tenant.security," +
        "com.medblaze.tenant.controllers," +
        "com.medblaze.tenant.document," +
        "com.medblaze.tenant.util," +
        "com.medblaze.dbservice.config," +
        "com.medblaze.tenant.exceptions," +
        "com.medblaze.appscommon.config")
public class ServiceConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

}
