package com.crossyf.dubbo.server.config;

import com.crossyf.dubbo.common.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SpringUtilConfig {

    @Bean
    public SpringUtil springUtil() {
        log.info("SpringUtil注入成功！");
        return new SpringUtil();
    }
}
