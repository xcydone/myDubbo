package com.crossyf.dubbo.springbatch.config;

import com.crossyf.dubbo.springbatch.ftp.FtpModle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpConfig {

    @Value("${ftp.ip}")
    private String ip;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.username}")
    private String userName;
    @Value("${ftp.password}")
    private String password;

    @Qualifier("ttssFtpConfig")
    @Bean
    public FtpModle ttssFtpConfig(@Autowired FtpTtssProperties ttssProperties) {
        FtpModle sftpModle = new FtpModle(ip, port, userName, password, ttssProperties.getSourceFilePath(),
                ttssProperties.getTempFilePath(), ttssProperties.getPrefix(), ttssProperties.getIsSingleFile());
        return sftpModle;
    }

}
