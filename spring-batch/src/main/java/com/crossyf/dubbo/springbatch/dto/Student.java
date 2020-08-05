package com.crossyf.dubbo.springbatch.dto;

import lombok.Data;

@Data
public class Student {
    private String firstName;
    private String lastName;

    public Student() {
    }

    @Override
    public String toString() {
        return firstName + "" + lastName;
    }
}
