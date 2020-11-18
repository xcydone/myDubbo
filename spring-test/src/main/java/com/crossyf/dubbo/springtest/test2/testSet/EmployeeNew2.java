package com.crossyf.dubbo.springtest.test2.testSet;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
public class EmployeeNew2 implements Serializable{

    private static final long serialVersionUID = 4591333637136912894L;

    private String name;

    private int age;

    private String address;

    private String hobbies;

    @Override
    public String toString() {
        return "EmployeeNew{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }
}
