package com.moodys.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmbeddedConfig {

    public static void main(String[] args) {
        SpringApplication.run(ContextConfig.class, args);

    }
}
