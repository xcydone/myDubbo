package com.crossyf.dubbo.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@EnableBatchProcessing
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
		JobParametersBuilder paramBuilder = new JobParametersBuilder();

		try{

			/* ------------- 场景一： localFileToMysqlJob or localDirectoryToMysqlJob------------- */
			/*Job localDirectoryToMysqlJob = (Job) context.getBean("localDirectoryToMysqlJob");
			paramBuilder.addLong("DS", System.currentTimeMillis());
			paramBuilder.addString("tableId", "1000");
			paramBuilder.addString("fileParentPath", "F:\\downLoadFile\\");
			JobExecution jobExecution = launcher.run(localDirectoryToMysqlJob, paramBuilder.toJobParameters());
*/
			/*Job localFileToMysqlJob = (Job) context.getBean("localFileToMysqlJob");
			paramBuilder.addLong("DS", System.currentTimeMillis());
			paramBuilder.addString("tableId", "1000");
			paramBuilder.addString("fileLocalPath", "F:\\downLoadFile\\BILL_INTER_TAXBAL_755-20200406-156351417.txt");
			JobExecution jobExecution = launcher.run(localFileToMysqlJob, paramBuilder.toJobParameters());*/


			/* ------------- 场景二：ftpToMysqlJob ------------- */
			/*Job ftpToMysqlJob = (Job) context.getBean("ftpToMysqlJob");
			paramBuilder.addLong("DS", System.currentTimeMillis());
			paramBuilder.addString("tableId", "1000");
			paramBuilder.addString("${yyyymm}", "202008");
			*//*paramBuilder.addString("${latn_id}", "755");*//*
			JobExecution jobExecution = launcher.run(ftpToMysqlJob, paramBuilder.toJobParameters());*/

			/*Job sftpToMysqlJob = (Job) context.getBean("sftpToMysqlJob");
			paramBuilder.addLong("DS", System.currentTimeMillis());
			paramBuilder.addString("tableId", "1000");
			paramBuilder.addString("${yyyymm}", "202008");
			paramBuilder.addString("${latn_id}", "755");
			JobExecution jobExecution = launcher.run(sftpToMysqlJob, paramBuilder.toJobParameters());*/

			/* ------------- 场景五：mysqlToFtpJob -------------*/
			/*Job mysqlToFtpJob = (Job) context.getBean("mysqlToFtpJob");
			paramBuilder.addLong("DS", System.currentTimeMillis());
			paramBuilder.addString("tableId", "1000");
			paramBuilder.addString("${billing_cycle_id}", "202007");
			paramBuilder.addString("${latn_id}", "755");
			paramBuilder.addString("size", "200");
			JobExecution jobExecution = launcher.run(mysqlToFtpJob, paramBuilder.toJobParameters());*/

			/*Job mysqlToSFtpJob = (Job) context.getBean("mysqlToSFtpJob");
			paramBuilder.addLong("DS", System.currentTimeMillis());
			paramBuilder.addString("tableId", "1000");
			paramBuilder.addString("${billing_cycle_id}", "202007");
			paramBuilder.addString("${latn_id}", "755");
			paramBuilder.addString("size", "200");
			JobExecution jobExecution = launcher.run(mysqlToSFtpJob, paramBuilder.toJobParameters());*/

			/* ------------ 场景三：A table to B table ---------
			Job job= (Job) context.getBean("dbToDbJob");
			paramBuilder.addLong("DS", System.currentTimeMillis());
			paramBuilder.addString("tableId", "1001");
			paramBuilder.addString("${dataId}", "400");
			paramBuilder.addString("${amount}", "50000000");
			JobExecution jobExecution = launcher.run(job, paramBuilder.toJobParameters());*/

			/* ------------ 场景四：A,B table to C table ---------
			Job job= (Job) context.getBean("dbToDbJob");
			paramBuilder.addLong("DS", System.currentTimeMillis());
			paramBuilder.addString("tableId", "1002");
			paramBuilder.addString("${billing_cycle_id}", "202007");
			JobExecution jobExecution = launcher.run(job, paramBuilder.toJobParameters());*/

			Job job= (Job) context.getBean("job7");
			paramBuilder.addLong("DS", System.currentTimeMillis());
			JobExecution jobExecution = launcher.run(job, paramBuilder.toJobParameters());


			System.out.println("JobExecution : " + jobExecution.toString());

			System.exit(0);
			return;
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
