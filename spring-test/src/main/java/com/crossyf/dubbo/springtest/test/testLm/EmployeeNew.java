package com.crossyf.dubbo.springtest.test.testLm;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
public class EmployeeNew implements Serializable{

    private static final long serialVersionUID = -1347056572219723517L;

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
