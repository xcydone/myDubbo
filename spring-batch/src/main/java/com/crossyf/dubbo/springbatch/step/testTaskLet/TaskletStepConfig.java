package com.crossyf.dubbo.springbatch.step.testTaskLet;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskletStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Tasklet firstTaskLet(){
        return new firstTaskLet();
    }

    @Bean
    public Tasklet secondTaskLet(){
        return new secondTaskLet();
    }

    @Bean
    public Tasklet threeTaskLet(){
        return new threeTaskLet();
    }

    @Bean
    public JobExecutionDecider myDecider(){
        return new MyDecider();
    }

    @Bean
    public JobExecutionDecider myFlowDecider(){
        return new MyFlowDecider();
    }

    @Bean
    public JobExecutionDecider myAddDecider(){
        return new MyAddDecider();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .tasklet(firstTaskLet())
                /*.tasklet(secondTaskLet())*/
                .build();
    }
    // tasklet写在一个步骤上时，第二个会覆盖第一个

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet(secondTaskLet())
                .build();
    }

    @Bean
    public Step step3(){
        return stepBuilderFactory.get("step3")
                .tasklet(threeTaskLet())
                .build();
    }


    @Bean
    public Flow jobFlowStep21(){
        return new FlowBuilder<Flow>("jobFlowStep21")
                .start(step2())
                .next(step1())
                .build();
    }

    @Bean
    public Flow jobFlowStep321(){
        return new FlowBuilder<Flow>("jobFlowStep321")
                .start(step3())
                .next(step2())
                .next(step1())
                .build();
    }
}
