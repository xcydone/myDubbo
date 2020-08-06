package com.crossyf.dubbo.springbatch.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing(modular=true)
public class JobConfiguration {
    @Bean
    public ApplicationContextFactory jobOne(){
        return new GenericApplicationContextFactory(JobOneConfig.class);
    }

    @Bean
    public ApplicationContextFactory jobTwo(){
        return new GenericApplicationContextFactory(JobTwoConfig.class);
    }
}
