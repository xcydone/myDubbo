package com.crossyf.dubbo.springtest.test.testYml;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
public class Popo2 {
    @Value("${hehe.haha2}")
    private String haha2;
}
