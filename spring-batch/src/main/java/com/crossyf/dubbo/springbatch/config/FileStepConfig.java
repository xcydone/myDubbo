package com.crossyf.dubbo.springbatch.config;

import com.crossyf.dubbo.springbatch.service.IConfTableExecutionService;
import com.crossyf.dubbo.springbatch.ftp.FtpModle;
import com.crossyf.dubbo.springbatch.ftp.FtpUtil;
import com.crossyf.dubbo.springbatch.ftp.SFtpUtil;
import com.crossyf.dubbo.springbatch.step.reader.CommonFileItemReader;
import com.crossyf.dubbo.springbatch.step.reader.CommonMultiResourceItemReader;
import com.crossyf.dubbo.springbatch.step.tasklet.FtpDownloadTaskLet;
import com.crossyf.dubbo.springbatch.step.tasklet.FtpUploadTaskLet;
import com.crossyf.dubbo.springbatch.step.tasklet.SftpDownloadTaskLet;
import com.crossyf.dubbo.springbatch.step.tasklet.SftpUploadTaskLet;
import com.crossyf.dubbo.springbatch.step.writer.CommonFileItemWriter;
import com.crossyf.dubbo.springbatch.step.writer.CommonMultiResourceItemWriter;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class FileStepConfig {

    @Autowired
    @Qualifier("ttssFtpConfig")
    private FtpModle ftpModle;

    @Autowired
    @Qualifier("ttssSFtpConfig")
    private FtpModle sftpModle;

    @Autowired
    private FtpUtil ftpUtil;

    @Autowired
    private SFtpUtil sftpUtil;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemProcessor commonProcessor;

    @Autowired
    private JdbcPagingItemReader commonJdbcItemReader;

    @Autowired
    private JdbcBatchItemWriter commonJdbcItemWriter;

    @Autowired
    private IConfTableExecutionService confTableExecutionService;

    @StepScope
    @Bean
    public Tasklet ftpDownloadTaskLet(@Value("#{jobParameters}") Map<String, JobParameter> params){
        return new FtpDownloadTaskLet(ftpModle, ftpUtil, params);
    }

    @Bean
    public Tasklet ftpUploadTaskLet(){
        return new FtpUploadTaskLet(ftpModle, ftpUtil);
    }

    @StepScope
    @Bean
    public Tasklet sftpDownloadTaskLet(@Value("#{jobParameters}") Map<String, JobParameter> params){
        return new SftpDownloadTaskLet(sftpModle, sftpUtil, params);
    }

    @Bean
    public Tasklet sftpUploadTaskLet(){
        return new SftpUploadTaskLet(sftpModle, sftpUtil);
    }

    @StepScope
    @Bean
    public FlatFileItemReader commonFileItemReaderByFileLocalPath(
            @Value("#{jobParameters[tableId]}") String tableId,
            @Value("#{jobParameters[fileLocalPath]}") String fileLocalPath) {
        return new CommonFileItemReader(confTableExecutionService, tableId, fileLocalPath);
    }

    @StepScope
    @Bean
    public FlatFileItemReader commonFileItemReader(@Value("#{jobParameters[tableId]}") String tableId) {
        return new CommonFileItemReader(confTableExecutionService, tableId);
    }

    @StepScope
    @Bean
    public MultiResourceItemReader commonMultiResourceItemReader(
            @Value("#{jobExecutionContext[filesPath]}") List<String> filesPath,
            @Value("#{jobParameters[tableId]}") String tableId){
        return new CommonMultiResourceItemReader(commonFileItemReader(tableId), filesPath);
    }

    @StepScope
    @Bean
    public MultiResourceItemReader commonMultiResourceItemReaderByDirectory(
            @Value("#{jobParameters[tableId]}") String tableId,
            @Value("#{jobParameters[fileParentPath]}") String fileParentPath){
        return new CommonMultiResourceItemReader(commonFileItemReader(tableId), fileParentPath);
    }

    @StepScope
    @Bean
    public FlatFileItemWriter commonFileItemWriter(@Value("#{jobParameters[tableId]}") String tableId){
        return new CommonFileItemWriter(confTableExecutionService, tableId);
    }

    @StepScope
    @Bean
    public MultiResourceItemWriter commonMultiResourceItemWriter(@Value("#{jobParameters}") Map<String, JobParameter> params){
        return new CommonMultiResourceItemWriter(ftpModle, params, confTableExecutionService, commonFileItemWriter(params.get("tableId").toString()));
    }

    @Bean
    public Step ftpDownLoadStep() {
        return stepBuilderFactory.get("ftpDownLoadStep")
                .tasklet(ftpDownloadTaskLet(null))
                .build();
    }

    @Bean
    public Step fileToMysqlStep() {
        return stepBuilderFactory.get("fileToMysqlStep")
                .<Map<String, Object>, Map<String, Object>>chunk(50)
                .reader(commonMultiResourceItemReader(null, null))
                .processor(commonProcessor)
                .writer(commonJdbcItemWriter)
                .build();
    }

    @Bean
    public Step localDirectoryToMysqlStep() {
        return stepBuilderFactory.get("localDirectoryToMysqlStep")
                .<Map<String, Object>, Map<String, Object>>chunk(50)
                .reader(commonMultiResourceItemReaderByDirectory(null, null))
                .processor(commonProcessor)
                .writer(commonJdbcItemWriter)
                .build();
    }

    @Bean
    public Step localFileToMysqlStep() {
        return stepBuilderFactory.get("localFileToMysqlStep")
                .<Map<String, Object>, Map<String, Object>>chunk(50)
                .reader(commonFileItemReaderByFileLocalPath(null, null))
                .processor(commonProcessor)
                .writer(commonJdbcItemWriter)
                .build();
    }

    @Bean
    public Step ftpUploadStep() {
        return stepBuilderFactory.get("ftpUploadStep")
                .tasklet(ftpUploadTaskLet())
                .build();
    }

    @Bean
    public Step mysqlToFileStep() {
        return stepBuilderFactory.get("mysqlToFileStep")
                .<Map<String, Object>, Map<String, Object>>chunk(50)
                .reader(commonJdbcItemReader)
                .processor(commonProcessor)
                .writer(commonMultiResourceItemWriter(null))
                .build();
    }

    @Bean
    public Step sftpDownLoadStep() {
        return stepBuilderFactory.get("sftpDownLoadStep")
                .tasklet(sftpDownloadTaskLet(null))
                .build();
    }

    @Bean
    public Step sftpUploadStep() {
        return stepBuilderFactory.get("sftpUploadStep")
                .tasklet(sftpUploadTaskLet())
                .build();
    }

}
