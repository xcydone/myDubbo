package com.crossyf.dubbo.springbatch.ftp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FtpModle implements InitializingBean {

    private String ip;

    private int port;

    private String userName;

    private String password;

    private String sourceFilePath;

    private String tempFilePath;

    private String prefix;

    private String isSingleFile;

    @Override
    public void afterPropertiesSet() throws Exception {
        org.apache.commons.io.FileUtils.forceMkdir(new File(tempFilePath));
    }

    public String toString() {
        return "FTPModel [ip=" + ip + ", userName=" + userName + ", password=" + password + ", port=" + port + ", sourceFilePath=" + sourceFilePath + ", tempFilePath=" + tempFilePath + ", prefix=" + prefix + "]";
    }

}
