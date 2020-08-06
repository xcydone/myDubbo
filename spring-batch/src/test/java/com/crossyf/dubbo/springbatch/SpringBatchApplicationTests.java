package com.crossyf.dubbo.springbatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/*@RunWith(SpringRunner.class)*/
@SpringBootTest/*(classes = {SpringBatchApplicationTests.BatchTestConfig.class})*/
/*public */class SpringBatchApplicationTests {

    @Test
    void contextLoads() {
    }

    /*@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @org.junit.Test
    public void testHelloWorldJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertThat(jobExecution.getExitStatus().getExitCode())
                .isEqualTo("COMPLETED");
    }

    @Configuration
    @Import({BatchConfig.class, HelloWorldJobConfig.class})
    static class BatchTestConfig {

        @Autowired
        private Job helloWorlJob;

        @Bean
        JobLauncherTestUtils jobLauncherTestUtils() throws NoSuchJobException {
            JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
            jobLauncherTestUtils.setJob(helloWorlJob);

            return jobLauncherTestUtils;
        }
    }*/

}
