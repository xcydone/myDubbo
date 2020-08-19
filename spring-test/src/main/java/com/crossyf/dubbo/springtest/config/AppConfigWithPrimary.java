package com.crossyf.dubbo.springtest.config;

import com.crossyf.dubbo.springtest.test.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfigWithPrimary {
    // 方法一： @Primary 注解表明哪一个bean需要优先被获取。
    // 方法二： 没有@Primary 注解 bean后面取别名
    // 方法二： 没有@Primary 注解 用 @Qualifier 可以区别开使用哪个bean  @Qualifier也可以和 @Autowired 一起使用注入

    @Bean/*("myBeanOne")*/
    @Qualifier("myBeanOne")
    public MyBean myBeanOne(){
        System.out.println("myBeanOne");
        return new MyBean();
    }

    @Bean/*("myBeanTwo")*/
    @Qualifier("myBeanTwo")
    /*@Primary*/
    public MyBean myBeanTwo(){
        System.out.println("myBeanTwo");
        return new MyBean();
    }
}
