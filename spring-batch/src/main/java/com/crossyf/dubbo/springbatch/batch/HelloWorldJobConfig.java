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
public class HelloWorldJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Bean
    public Job helloWorldJob() {
        // 创建job
        return jobBuilders.get("helloWorldJob")
                .start(helloWorldStep())
                /*.next(nihaoStep(stepBuilders))*/
                .build();
    }

    @Bean
    public Step helloWorldStep() {
        // 创建step1
        return stepBuilders.get("helloWorldStep")
                .<Student, String>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<Student> reader() {
        // 读取数据以student类存放
        return new FlatFileItemReaderBuilder<Student>()
                .name("studentItemReader")
                .resource(new ClassPathResource("csv/student.csv"))
                .delimited().names(new String[] {"firstName", "lastName"})
                .targetType(Student.class).build();
    }

    @Bean
    public StudentItemProcessor processor() {
        // 处理每行数据
        return new StudentItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<String> writer() {
        // 写入文件
        return new FlatFileItemWriterBuilder<String>()
                .name("greetingItemWriter")
                .resource(new FileSystemResource("target/test-outputs/greetings.txt"))
                .lineAggregator(new PassThroughLineAggregator<>()).build();
    }

    /*@Bean
    public Step nihaoStep(StepBuilderFactory stepBuilders) {
        // 创建step2
        return stepBuilders.get("nihaoStep")
                .<Student, String>chunk(10)
                .reader()
                .processor(processor2())
                .writer(writer2())
                .build();
    }

    @Bean
    public FlatFileItemReader<Student> reader() {
        // 读取数据以student类存放
        return new FlatFileItemReaderBuilder<Student>()
                .name("studentItemReader")
                .resource(new ClassPathResource("csv/student.csv"))
                .delimited().names(new String[] {"firstName", "lastName"})
                .targetType(Student.class).build();
    }

    @Bean
    public NihaoItemProcessor processor2() {
        // 处理每行数据
        return new NihaoItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<String> writer2() {
        // 写入文件
        return new FlatFileItemWriterBuilder<String>()
                .name("greetingItemWriter")
                .resource(new FileSystemResource("target/test-outputs/greetings2.txt"))
                .lineAggregator(new PassThroughLineAggregator<>()).build();
    }*/
}
