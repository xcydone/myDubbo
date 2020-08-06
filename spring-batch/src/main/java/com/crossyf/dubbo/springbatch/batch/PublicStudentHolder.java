package com.crossyf.dubbo.springbatch.batch;

import com.crossyf.dubbo.springbatch.dto.StudentDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublicStudentHolder {
    private List<StudentDto> publicStudentList;

    public List<StudentDto> getPublicStudentList() {
        return publicStudentList;
    }

    public void setPublicStudentList(List<StudentDto> publicStudentList) {
        this.publicStudentList = publicStudentList;
    }
}
