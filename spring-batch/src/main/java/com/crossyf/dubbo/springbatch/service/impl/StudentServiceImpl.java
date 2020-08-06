package com.crossyf.dubbo.springbatch.service.impl;

import com.crossyf.dubbo.springbatch.mapper.StudentMapper;
import com.crossyf.dubbo.springbatch.service.IStudentService;
import com.crossyf.dubbo.springbatch.dto.StudentDto;
import com.crossyf.dubbo.springbatch.entity.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void insertBatch(List<StudentDto> studentDtoList) {

        if(studentDtoList != null && studentDtoList.size() != 0){
            List<Student> studentList = studentDtoList.stream().map(p -> {
                Student st = new Student();
                BeanUtils.copyProperties(p, st);
                st.setId(UUID.randomUUID().toString());
                return st;
            }).collect(Collectors.toList());

            studentMapper.insertBatch(studentList);
        }
    }
}
