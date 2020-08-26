package com.crossyf.dubbo.springbatch.step.testTaskLet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskletJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private Step step1;

    @Autowired
    private Step step2;

    @Autowired
    private Step step3;

    @Autowired
    private Flow jobFlowStep321;

    @Autowired
    private Flow jobFlowStep21;

    @Autowired
    private JobExecutionDecider myDecider;

    @Autowired
    private JobExecutionDecider myFlowDecider;

    @Autowired
    private JobExecutionDecider myAddDecider;


    @Bean
    public Job job1() {
        return jobBuilderFactory.get("job1")
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Job job2() {
        return jobBuilderFactory.get("job2")
                .start(step1).on("COMPLETED").to(step2)
                .from(step2).on("COMPLETED").to(step3).end()
                .build();
    }

    @Bean
    public Job job3(){
        return jobBuilderFactory.get("job3")
                .start(step1)
                .next(myDecider)
                .from(myDecider).on("next2").to(step2).next(step1)
                .next(myDecider).on("next3").to(step3).next(step2).next(step1)
                .next(myDecider).on("*").to(myDecider).end()
                .build();
    }

    @Bean
    public Job job4(){
        return jobBuilderFactory.get("job4")
                .start(step1)
                .next(myAddDecider)
                .from(myFlowDecider).on("next2").to(step2)
                .next(myAddDecider).on("next3").to(step3)
                .next(myAddDecider).on("*").to(myAddDecider)
                .end()
                .build();
    }

    @Bean
    public Job job5(){
        return jobBuilderFactory.get("job5")
                .start(step1)
                .next(myDecider)
                .from(myDecider).on("next2").to(jobFlowStep21)
                .next(myDecider).on("next3").to(jobFlowStep321)
                .next(myDecider).on("*").to(myDecider).end()
                .build();
    }

    @Bean
    public Job job6(){
        return jobBuilderFactory.get("job6")
                .start(step1)
                .next(myFlowDecider)
                .from(myFlowDecider).on("ODD").to(step2)
                .from(step2).on("*").to(step3)
                .end()
                .build();
    }

    @Bean
    public Job job7(){
        return jobBuilderFactory.get("job7")
                .start(step1)
                .next(myFlowDecider)
                .from(myFlowDecider).on("EVEN").to(step2)
                .from(myFlowDecider).on("ODD").to(step3)
                .from(myFlowDecider).on("*").to(myFlowDecider)
                .end()
                .build();
    }

    @Bean
    public Job job10(){
        SimpleJobBuilder jobStart0 = jobBuilderFactory.get("job10").start(step1);
        SimpleJobBuilder startStep = jobStart0;
        for(int i = 0 ; i < 2; i++){
            SimpleJobBuilder nextStep = startStep.next(step2).next(step3);
            startStep = nextStep;
        }
        return startStep.build();
    }

}
