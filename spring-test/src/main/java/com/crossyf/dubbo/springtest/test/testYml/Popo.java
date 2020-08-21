package com.crossyf.dubbo.springtest.test.testYml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "hehe")
public class Popo {
    private String haha;
}
