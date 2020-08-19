package com.crossyf.dubbo.springtest.test;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyBean {
    public MyBean(){
        System.out.println("MyBean Initializing");
    }

    @PostConstruct
    public void init(){
        System.out.println("Bean 初始化方法被调用");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Bean 销毁方法被调用");
    }
}

