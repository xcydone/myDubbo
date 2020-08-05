package com.crossyf.dubbo.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Date;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBatchApplication {

    public static void main(String[] args)
            throws BeansException,
            JobExecutionAlreadyRunningException,
            JobRestartException,
            JobInstanceAlreadyCompleteException,
            JobParametersInvalidException,
            InterruptedException,
            IOException {

        /*SpringApplication.run(SpringBatchApplication.class, args);*/

        boolean oneJob = false;
        boolean twoJob = false;

        SpringApplication application = new SpringApplication(SpringBatchApplication.class);
        /*application.setWebEnvironment(false);*/
        ConfigurableApplicationContext ctx = application.run(args);
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);

        //无参数时，都执行
        if (args.length != 0) {
            for (String arg : args) {
                if (arg.toLowerCase().equals("?")) {
                    System.out.println("Usage : Batch [-b] [-r]");
                }
                if (arg.toLowerCase().equals("-o") || arg == null) {
                    oneJob = true;
                }
                if (arg.toLowerCase().equals("-t")) {
                    twoJob = true;
                }
            }
        }
        if (oneJob) {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .toJobParameters();
            jobLauncher.run(ctx.getBean("helloWorldJob", Job.class), jobParameters);
            //jobOne名称必须和JobOneConfiguration中配置的@bean Job 的方法名一致，后面jobTwo也是一样。
        }
        if (twoJob) {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .toJobParameters();
            jobLauncher.run(ctx.getBean("helloWorldJob2", Job.class), jobParameters);
        }

        System.exit(0);
    }

}
