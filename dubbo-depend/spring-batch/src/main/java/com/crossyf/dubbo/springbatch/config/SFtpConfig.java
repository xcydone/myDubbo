package com.crossyf.dubbo.springbatch.config;

import com.crossyf.dubbo.springbatch.ftp.FtpModle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SFtpConfig {

    @Value("${sftp.ip}")
    private String ip;
    @Value("${sftp.port}")
    private int port;
    @Value("${sftp.username}")
    private String userName;
    @Value("${sftp.password}")
    private String password;

    @Qualifier("ttssSFtpConfig")
    @Bean
    public FtpModle ttssSFtpConfig(@Autowired SFtpTtssProperties ttssProperties) {
        FtpModle sftpModle = new FtpModle(ip, port, userName, password, ttssProperties.getSourceFilePath(),
                ttssProperties.getTempFilePath(), ttssProperties.getPrefix(), ttssProperties.getIsSingleFile());
        return sftpModle;
    }

}
