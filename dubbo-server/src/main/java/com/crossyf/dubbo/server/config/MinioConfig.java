package com.crossyf.dubbo.server.config;

import com.crossyf.dubbo.common.utils.MinioTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @ConfigurationProperties(prefix = "minio")
    @Bean
    public MinioTemplate minioTemplate(){
        return new MinioTemplate();
    }
}
