package com.crossyf.dubbo.springtest.test2.testSet;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeNew that = (EmployeeNew) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
