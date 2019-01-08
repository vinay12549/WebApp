package com.medblaze.dbservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.medblaze.sqladapter.config," +
        "com.medblaze.dbservice.impl")
public class DBServiceConfig {

}
