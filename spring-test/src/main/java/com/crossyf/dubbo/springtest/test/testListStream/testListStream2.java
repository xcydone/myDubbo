package com.crossyf.dubbo.springtest.test.testListStream;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class testListStream2 {
    public static void main(String[] args){
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee().setName("zhangs").setAge(12).setAddress("武汉");
        employeeList.add(employee1);
        Employee employee2 = new Employee().setName("lis").setAge(13).setAddress("武汉");
        employeeList.add(employee2);
        Employee employee3 = new Employee().setName("ww").setAge(14).setAddress("武汉");
        employeeList.add(employee3);
        Employee employee4 = new Employee().setName("zhangs").setAge(14).setAddress("北京");
        employeeList.add(employee4);
        Employee employee5 = new Employee().setName("lis").setAge(15).setAddress("武汉");
        employeeList.add(employee5);
        Employee employee6 = new Employee().setName("ww").setAge(14).setAddress("北京");
        employeeList.add(employee6);
        Employee employee7 = new Employee().setName("zhangs").setAge(13).setAddress("武汉");
        employeeList.add(employee7);
        Employee employee8 = new Employee().setName("lis").setAge(12).setAddress("北京");
        employeeList.add(employee8);
        Employee employee9 = new Employee().setName("lis").setAge(12).setAddress("北京");
        employeeList.add(employee9);

        /*// listFilter和listff一样
        List<Employee2> listFilter = new ArrayList<>();
        List<Employee2> listff = employeeList.stream().filter(filterItem -> {
            Boolean isExist = listFilter.stream().anyMatch( item -> item.getAge() == filterItem.getAge());
            if(!isExist){
                listFilter.add(filterItem);
                return true;
            }
            return false;

        }).collect(Collectors.toList());
        System.out.println(listff.toString());*/

        Map<Object, Employee> map = employeeList.stream().collect(Collectors.toMap(dto -> dto.getAge(), dto -> dto, (o1, o2) -> o1));
        List<Employee> list = map.entrySet().stream().map(e -> {
            Employee ne = new Employee();
            BeanUtils.copyProperties(e.getValue(), ne);
            return ne;
        }).collect(Collectors.toList());
        System.out.println(map.toString());
        System.out.println(list.toString());

        /*int df = employeeList.stream().reduce( (a, b) -> {
            return new Employee2().setAge(a.getAge() +b.getAge());
        }).get().getAge();


        *//*employeeList.forEach((item)-> employeetmp.removeIf((items)->item.getAge()==items.getAge()));*//*
        System.out.println(df);*/
    }
}
