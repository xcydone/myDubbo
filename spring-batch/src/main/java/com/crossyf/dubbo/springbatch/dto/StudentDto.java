package com.crossyf.dubbo.springbatch.dto;

import lombok.Data;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;

    public StudentDto() {
    }

    @Override
    public String toString() {
        return firstName + "" + lastName;
    }
}
