package com.crossyf.dubbo.server.config;

import com.crossyf.dubbo.common.utils.HBaseUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HBaseUtilConfig {
    /*@ConfigurationProperties(prefix = "hbase")*/
    @Bean
    public HBaseUtil HBaseUtil(){
        return new HBaseUtil();
    }
}
