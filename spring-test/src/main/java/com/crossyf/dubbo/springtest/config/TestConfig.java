package com.crossyf.dubbo.springtest.config;

import com.crossyf.dubbo.springtest.test.beanTest.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    /*
    * @Bean(initMethod = "init", destroyMethod = "destroy")
    * 注解： 等同于在init方法上加  @PostConstruct 注解 和 destroy 方法上加  @PreDestroy 注解
    * */
    @Bean
    public MyBean myBean(){
        return new MyBean();
    }
}
