package com.crossyf.dubbo.springtest.test2.testSet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class dataType {
    public static void main(String[] args) {
        EmployeeNew o1 = EmployeeNew.builder().name("张A").age(40).address("东湖").hobbies("4吃饭").build();
        EmployeeNew o2 = EmployeeNew.builder().name("张E").age(40).address("西湖").hobbies("2睡觉").build();
        EmployeeNew o3 = EmployeeNew.builder().name("赵E").age(30).address("西湖").hobbies("2睡觉").build();
        EmployeeNew o4 = EmployeeNew.builder().name("张A").age(30).address("南湖").hobbies("2睡觉").build();
        EmployeeNew o5 = EmployeeNew.builder().name("张V").age(30).address("南湖").hobbies("2睡觉").build();

        List<EmployeeNew> emls = new ArrayList<>();

        emls.add(o1);
        emls.add(o2);
        emls.add(o3);
        emls.add(o4);
        emls.add(o5);

        // list获取两个属性转成map
        Map<String, Integer> nameToAgeMap = emls.stream()
                .collect(Collectors.toMap(EmployeeNew::getName,EmployeeNew::getAge, (o21, o22)->o21));
        System.out.println(nameToAgeMap.toString()); // {张A=40, 张V=30, 张E=40}

        Set<Map.Entry<String, Integer>> sets = nameToAgeMap.entrySet();
        sets.forEach(set->{
            System.out.println(set.getKey() + " -> " + set.getValue());
        });

        Set<String> keys = nameToAgeMap.keySet();
        keys.forEach(key->{
            System.out.println(key + " --> " + nameToAgeMap.get(key));
        });

        Set<Map.Entry<String, Integer>> entrySet = nameToAgeMap.entrySet();

        // Comparator comparator = Comparator.comparing(EmployeeNew::getAge);
        Set<EmployeeNew> setEmp = new TreeSet<>(Comparator.comparing(EmployeeNew::getAge));

        setEmp.add(o1);
        setEmp.add(o2);
        setEmp.add(o3);
        setEmp.add(o4);
        setEmp.add(o5);
        System.out.println(setEmp.size());

        setEmp.forEach(k->{
            System.out.println(k.toString());
        });

        // treeset要放比较器
        /*Set<EmployeeNew> setEmpUnOrder = new TreeSet<>();
        setEmpUnOrder.add(o1);
        setEmpUnOrder.add(o2);
        setEmpUnOrder.add(o3);
        setEmpUnOrder.add(o4);
        setEmpUnOrder.add(o5);
        System.out.println(setEmpUnOrder.size());*/

        /*Set<EmployeeNew> setEmpUnOrder = new HashSet<>();
        setEmpUnOrder.add(o1);
        setEmpUnOrder.add(o2);
        setEmpUnOrder.add(o3);
        setEmpUnOrder.add(o4);
        setEmpUnOrder.add(o5);
        System.out.println(setEmpUnOrder.size());*/

        LocalDateTime localDateTime = LocalDateTime.now();

        final String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));


        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00")));
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE));
    }
}
