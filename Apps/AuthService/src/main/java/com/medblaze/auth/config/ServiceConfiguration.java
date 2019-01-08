package com.medblaze.auth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.medblaze.auth.controllers," +
        "com.medblaze.auth.document")
public class ServiceConfiguration {

}
