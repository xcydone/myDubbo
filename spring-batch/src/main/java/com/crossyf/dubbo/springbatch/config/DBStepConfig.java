package com.crossyf.dubbo.springbatch.config;

import com.crossyf.dubbo.springbatch.service.IConfTableExecutionService;
import com.crossyf.dubbo.springbatch.util.SpringUtil;
import com.crossyf.dubbo.springbatch.step.processor.CommonProcessor;
import com.crossyf.dubbo.springbatch.step.reader.CommonJdbcItemReader;
import com.crossyf.dubbo.springbatch.step.writer.CommonJdbcItemWriter;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DBStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IConfTableExecutionService confTableExecutionService;

    @Autowired
    private SpringUtil springUtil;

    @Bean
    @StepScope
    public JdbcPagingItemReader commonJdbcItemReader(@Value("#{jobParameters}") Map<String, JobParameter> jobMap) {
        return new CommonJdbcItemReader(dataSource, jobMap, confTableExecutionService);
    }

    @Bean
    @StepScope
    public ItemProcessor commonProcessor(@Value("#{jobParameters[tableId]}") String tableId){
        return new CommonProcessor(tableId, confTableExecutionService, springUtil);
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter commonJdbcItemWriter(@Value("#{jobParameters}") Map<String, JobParameter>jobMap){
        return new CommonJdbcItemWriter(dataSource, jobMap, confTableExecutionService);
    }

    @Bean
    public Step dbToDbStep() {
        return stepBuilderFactory.get("dbToDbStep")
                .<Map<String, Object>, Map<String, Object>>chunk(50)
                .reader(commonJdbcItemReader(null))
                .processor(commonProcessor(null))
                .writer(commonJdbcItemWriter(null))
                .build();
    }
}
