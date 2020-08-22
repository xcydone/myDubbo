package com.crossyf.dubbo.springtest.config;

import com.crossyf.dubbo.springtest.test.beanTest.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@Lazy*/
@Configuration
public class AppConfigWithLazy {
    /*@Bean
    public MyBean myBean(){
        System.out.println("myBean Initialized");
        return new MyBean();
    }

    @Bean
    public MyBean IfLazyInit(){
        System.out.println("initialized");
        return new MyBean();
    }*/
}
