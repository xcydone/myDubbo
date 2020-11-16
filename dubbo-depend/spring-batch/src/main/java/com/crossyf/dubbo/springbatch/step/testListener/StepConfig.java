package com.crossyf.dubbo.springbatch.step.testListener;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public LinesReader linesReader() {
        return new LinesReader();
    }

    @Bean
    public LinesProcessor linesProcessor() {
        return new LinesProcessor();
    }

    @Bean
    public LinesWriter linesWriter() {
        return new LinesWriter();
    }


    @Bean
    protected Step readLinesStep() {
        return steps
                .get("readLinesStep")
                .tasklet(linesReader())
                .build();
    }

    @Bean
    protected Step processLinesStep() {
        return steps
                .get("processLinesStep")
                .tasklet(linesProcessor())
                .build();
    }

    @Bean
    protected Step writeLinesStep() {
        return steps
                .get("writeLinesStep")
                .tasklet(linesWriter())
                .build();
    }
}
