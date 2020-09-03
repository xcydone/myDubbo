package com.crossyf.dubbo.springtest.test.testEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class mailConfig {
    @Autowired
    private MailProperties properties;

    @Bean
    public SendEmail getSendMail(){
        String from = "1641265515@qq.com";

        String to1 = "1641265515@qq.com";
        String to2 = "1641265515@qq.com";
        List<String> to = new ArrayList<>();
        to.add(to1);
        to.add(to2);
        return new SendEmail(from, to, properties);
    }
}
