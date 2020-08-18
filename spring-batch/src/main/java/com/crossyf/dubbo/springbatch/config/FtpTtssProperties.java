package com.crossyf.dubbo.springbatch.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "ftp.ttss")
public class FtpTtssProperties {

    private String sourceFilePath;

    private String tempFilePath;

    private String prefix;

    private String isSingleFile;

}
