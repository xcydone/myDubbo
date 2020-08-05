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
    public ApplicationContextFactory HelloWorldJob(){
        return new GenericApplicationContextFactory(HelloWorldJobConfig.class);
    }

    @Bean
    public ApplicationContextFactory HelloWorldJob2(){
        return new GenericApplicationContextFactory(HelloWorldJob2Config.class);
    }
}
