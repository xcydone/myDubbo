package com.crossyf.dubbo.springbatch.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "sftp.ttss")
public class SFtpTtssProperties {

    private String sourceFilePath;

    private String tempFilePath;

    private String prefix;

    private String isSingleFile;

}
