package com.crossyf.dubbo.springtest.config;

import com.crossyf.dubbo.springtest.test.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("like")
@Configuration
public class AppConfigWithActiveProfile {
    @Bean
    public Subject subject(){
        Subject subject = new Subject();
        subject.setLike("物理");
        return subject;
    }


    // https://www.cnblogs.com/cxuanBlog/p/11179439.html
}
