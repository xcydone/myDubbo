package com.crossyf.dubbo.springtest.config;

import com.crossyf.dubbo.springtest.test.beanTest.FirstBean;
import com.crossyf.dubbo.springtest.test.beanTest.SecondBean;
import com.crossyf.dubbo.springtest.test.beanTest.ThirdBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@DependsOn
@Configuration
public class AppConfigWithDependsOn {

    // @DependsOn注释到方法上时，会根据方法依赖，调整初始化的先后问题，依次顺序是 secondBean thirdBean firstBean
    // @DependsOn注释到类上时，自动解决方法依赖问题，依次顺序是 firstBean secondBean thirdBean

    @Bean("firstBean")
    /*@DependsOn(value = {
            "secondBean",
            "thirdBean"
    })*/
    public FirstBean firstBean() {
        return new FirstBean();
    }

    @Bean("secondBean")
    public SecondBean secondBean() {
        return new SecondBean();
    }

    @Bean("thirdBean")
    public ThirdBean thirdBean() {
        return new ThirdBean();
    }
}
