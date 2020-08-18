package com.crossyf.dubbo.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private Step ftpDownLoadStep;

    @Autowired
    private Step fileToMysqlStep;

    @Autowired
    private Step localDirectoryToMysqlStep;

    @Autowired
    private Step localFileToMysqlStep;

    @Autowired
    private Step ftpUploadStep;

    @Autowired
    private Step mysqlToFileStep;

    @Autowired
    private Step dbToDbStep;

    @Autowired
    private Step sftpDownLoadStep;

    @Autowired
    private Step sftpUploadStep;

    @Bean
    public Job ftpToMysqlJob() {
        return jobBuilderFactory.get("ftpToMysqlJob")
                .start(ftpDownLoadStep)
                .next(fileToMysqlStep)
                .build();
    }

    @Bean
    public Job localDirectoryToMysqlJob() {
        return jobBuilderFactory.get("localDirectoryToMysqlJob")
                .start(localDirectoryToMysqlStep)
                .build();
    }

    @Bean
    public Job localFileToMysqlJob() {
        return jobBuilderFactory.get("localFileToMysqlJob")
                .start(localFileToMysqlStep)
                .build();
    }

    @Bean
    public Job mysqlToFtpJob() {
        return jobBuilderFactory.get("mysqlToFtpJob")
                .start(mysqlToFileStep)
                .next(ftpUploadStep)
                .build();
    }

    @Bean
    public Job dbToDbJob() {
        return jobBuilderFactory.get("dbToDbJob")
                .start(dbToDbStep)
                .build();
    }

    @Bean
    public Job sftpToMysqlJob() {
        return jobBuilderFactory.get("sftpToMysqlJob")
                .start(sftpDownLoadStep)
                .next(fileToMysqlStep)
                .build();
    }

    @Bean
    public Job mysqlToSFtpJob() {
        return jobBuilderFactory.get("mysqlToSFtpJob")
                .start(mysqlToFileStep)
                .next(sftpUploadStep)
                .build();
    }

}
