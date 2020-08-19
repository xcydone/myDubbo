package com.crossyf.dubbo.springtest.config;


import com.crossyf.dubbo.springtest.test.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("wenke")
@Configuration
public class AppConfigWithInActiveProfile {
    @Bean
    public Subject subject(){
        Subject subject = new Subject();
        subject.setWenke("历史");
        return subject;
    }
}
