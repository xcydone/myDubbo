package com.crossyf.dubbo.springbatch.batch;

import com.crossyf.dubbo.springbatch.dto.StudentDto;
import com.crossyf.dubbo.springbatch.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class JobOneStepOneProcessor implements ItemProcessor<StudentDto, String> {
    // 快速创建日志实例
    private static final Logger LOGGER = LoggerFactory.getLogger(JobOneStepOneProcessor.class);

    @Autowired
    private PublicStudentHolder studentHolder;

    @Override
    public String process(StudentDto student) throws Exception {
        /*StudentDto st = new StudentDto();
        BeanUtils.copyProperties(student, st);
        List<StudentDto> sl = studentHolder.getPublicStudentList();
        if(sl != null && sl.size() > 0){
            studentHolder.getPublicStudentList().add(st);
        }else{
            sl = new ArrayList<>();
            sl.add(st);
            studentHolder.setPublicStudentList(sl);
        }*/


        String greeting = "Hello " + student.getFirstName() + " "
                + student.getLastName() + "!";

        LOGGER.info("converting '{}' into '{}'", student, greeting);

        return greeting;
    }
}
