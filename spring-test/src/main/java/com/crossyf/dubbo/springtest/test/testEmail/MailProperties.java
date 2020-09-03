package com.crossyf.dubbo.springtest.test.testEmail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    private String host;

    private String username;

    private String password;

    private String filePath;
}
