package com.crossyf.dubbo.springtest.test.testListStream;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Employee implements Serializable{

    private static final long serialVersionUID = -1347056570219723517L;

    private String name;

    private int age;

    private String address;

    @Override
    public String toString() {
        return "Employee2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
