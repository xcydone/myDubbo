package com.crossyf.dubbo.springbatch.batch;

import com.crossyf.dubbo.springbatch.dto.Student;
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
public class HelloWorldJob2Config {
    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Bean
    public Job helloWorldJob2() {
        // 创建job
        return jobBuilders.get("helloWorldJob2")
                .start(helloWorldStep2())
                .build();
    }

    @Bean
    public Step helloWorldStep2() {
        // 创建step1
        return stepBuilders.get("helloWorldStep2")
                .<Student, String>chunk(10)
                .reader(reader2())
                .processor(processor2())
                .writer(writer2())
                .build();
    }

    @Bean
    public FlatFileItemReader<Student> reader2() {
        // 读取数据以student类存放
        return new FlatFileItemReaderBuilder<Student>()
                .name("studentItemReader")
                .resource(new ClassPathResource("csv/student2.csv"))
                .delimited().names(new String[] {"firstName", "lastName"})
                .targetType(Student.class).build();
    }

    @Bean
    public StudentItemProcessor2 processor2() {
        // 处理每行数据
        return new StudentItemProcessor2();
    }

    @Bean
    public FlatFileItemWriter<String> writer2() {
        // 写入文件
        return new FlatFileItemWriterBuilder<String>()
                .name("greetingItemWriter2")
                .resource(new FileSystemResource("target/test-outputs/greetings2.txt"))
                .lineAggregator(new PassThroughLineAggregator<>()).build();
    }
}
