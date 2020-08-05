package com.crossyf.dubbo.springbatch.batch;

import com.crossyf.dubbo.springbatch.dto.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class StudentItemProcessor implements ItemProcessor<Student, String> {
    // 快速创建日志实例
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentItemProcessor.class);

    @Override
    public String process(Student student) throws Exception {
        String greeting = "Hello " + student.getFirstName() + " "
                + student.getLastName() + "!";

        LOGGER.info("converting '{}' into '{}'", student, greeting);
        return greeting;
    }
}
