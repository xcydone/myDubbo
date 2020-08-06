package com.crossyf.dubbo.springbatch.batch;

import com.crossyf.dubbo.springbatch.dto.StudentDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class JobTwoConfig {
    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Bean
    public Job jobTwo() {
        // 创建job
        return jobBuilders.get("jobTwo")
                .start(JobTwoStepOne())
                .build();
    }

    @Bean
    public Step JobTwoStepOne() {
        // 创建step1
        return stepBuilders.get("JobTwoStepOne")
                .<StudentDto, String>chunk(10)
                .reader(JobTwoStepOneReader())
                .processor(JobTwoStepOneProcessor())
                .writer(JobTwoStepOneWriter())
                .build();
    }

    @Bean
    public FlatFileItemReader<StudentDto> JobTwoStepOneReader() {
        // 读取数据以student类存放
        return new FlatFileItemReaderBuilder<StudentDto>()
                .name("JobTwoStepOneReader")
                .resource(new ClassPathResource("csv/student2.csv"))
                .delimited().names(new String[] {"firstName", "lastName"})
                .targetType(StudentDto.class).build();
    }

    @Bean
    public JobTwoStepOneProcessor JobTwoStepOneProcessor() {
        // 处理每行数据
        return new JobTwoStepOneProcessor();
    }

    @Bean
    public FlatFileItemWriter<String> JobTwoStepOneWriter() {
        // 写入文件
        return new FlatFileItemWriterBuilder<String>()
                .name("JobTwoStepOneWriter")
                .resource(new FileSystemResource("target/test-outputs/greetings2.txt"))
                .lineAggregator(new PassThroughLineAggregator<>()).build();
    }
}
