package com.crossyf.dubbo.springbatch.service;

import com.crossyf.dubbo.springbatch.dto.StudentDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IStudentService {
    //写入数据到库
    public void insertBatch(List<StudentDto> studentDtoList);
}
