package com.crossyf.dubbo.springtest.test2.testLm;

import java.util.*;
import java.util.stream.Collectors;

public class listStreamNew {
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

        // list获取一个属性List（没啥必要，直接循环就好）
        List<String> nameLists = emls.stream().map(EmployeeNew::getName).collect(Collectors.toList());
        System.out.println(nameLists.toString()); // [张A, 张E, 张A, 张V]

        // list获取一个属性List（去重）
        List<String> nameListsDistinct = emls.stream().map(EmployeeNew::getName).distinct().collect(Collectors.toList());
        System.out.println(nameListsDistinct.toString()); // [张A, 张E, 张V]

        // list获取一个属性List（去重）
        List<Integer> ageListsDistinct = emls.stream().map(EmployeeNew::getAge).distinct().collect(Collectors.toList());
        System.out.println(ageListsDistinct.toString()); // [40, 30]

        // list获取一个属性set（去重，无序）
        Set<String> nameSets = emls.stream().map(EmployeeNew::getName).collect(Collectors.toSet());
        System.out.println(nameSets.toString()); // [张A, 张V, 张E]

        // list获取一个属性set（去重，正序）
        Set<String> nameLinkedHashSets = emls.stream().map(EmployeeNew::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(nameLinkedHashSets.toString()); // [张A, 张E, 张V]

        // list获取一个属性set（去重，倒序）
        Set<String> nameLinkedHashSetsReverse = emls.stream().map(EmployeeNew::getName)
                .sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(nameLinkedHashSetsReverse.toString()); // [张V, 张E, 张A]

        // list获取一个属性String
        String nameStr = nameLinkedHashSetsReverse.stream().collect(Collectors.joining(",","[","]"));
        System.out.println(nameStr); // [张V,张E,张A]

        // list获取两个属性转成map
        Map<String, Integer> nameToAgeMap = emls.stream()
                .collect(Collectors.toMap(EmployeeNew::getName,EmployeeNew::getAge, (o21,o22)->o21));
        System.out.println(nameToAgeMap.toString()); // {张A=40, 张V=30, 张E=40}

        // list分组之后变为map
        Map<Integer, Map<String, List<EmployeeNew>>> ageEmployee = emls.stream().collect(Collectors.groupingBy(
                EmployeeNew::getAge, Collectors.groupingBy(EmployeeNew::getName)));
        ageEmployee.forEach((k1,v1)->{
            System.out.println(k1 + " -> " + v1.toString());
        });
        // 40 -> {张A=[Employee2{name='张A', age=40, address='东湖', hobbies='4吃饭'}], 张E=[Employee2{name='张E', age=40, address='西湖', hobbies='2睡觉'}]}
        //30 -> {张A=[Employee2{name='张A', age=30, address='南湖', hobbies='2睡觉'}], 张V=[Employee2{name='张V', age=30, address='南湖', hobbies='2睡觉'}]}


        // 过滤某个字段，姓张
        // indexOf contains startsWith
        List<EmployeeNew> emAgeList = emls.stream().filter(w -> w.getAge() == 30 && w.getName().contains("张")).collect(Collectors.toList());
        System.out.println(emAgeList.toString());


        // 累加
        Integer reduce = emls.stream().map(EmployeeNew::getAge).reduce((x1, x2) -> x1 + x2).get().intValue();
        /*Integer ages = ;*/
        System.out.println(reduce);

        // 循环
        /*emls.stream().forEach(EmployeeNew -> {
             System.out.println(EmployeeNew.getAge());
        });*/

        // 获取最小的年龄
        Integer ageMin = emls.stream().mapToInt(EmployeeNew::getAge).min().getAsInt();
        System.out.println(ageMin);

        // 获取最小年龄的结构体
        EmployeeNew minEmployee = emls.stream().min(Comparator.comparing(EmployeeNew::getAge)).get();
        System.out.println(minEmployee.toString());

    }
}
