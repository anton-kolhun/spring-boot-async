package com.moodys.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.Executor;

@Configuration
@ComponentScan("com.moodys")
@EnableWebMvc
@EnableAutoConfiguration
@EnableAsync
public class ContextConfig extends AsyncConfigurerSupport {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
       //executor.setMaxPoolSize(6);
        executor.setQueueCapacity(0);// once reached - core increases by 1 (until max pool size)
        executor.setThreadNamePrefix("AsyncThread-");
        executor.initialize();
        return executor;
    }
}


