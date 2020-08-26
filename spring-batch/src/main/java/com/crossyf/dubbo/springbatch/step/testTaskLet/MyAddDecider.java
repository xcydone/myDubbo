package com.crossyf.dubbo.springbatch.step.testTaskLet;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class MyAddDecider implements JobExecutionDecider {

    private int count = 1;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        count++;
        if (count<10){
            System.out.println(count);
            System.out.println("next2");
            return new FlowExecutionStatus("next2");

        }
        else if (count<20){
            System.out.println(count);
            System.out.println("next3");
            return new FlowExecutionStatus("next3");
        }
        else {
            return FlowExecutionStatus.STOPPED;
        }
    }
}
