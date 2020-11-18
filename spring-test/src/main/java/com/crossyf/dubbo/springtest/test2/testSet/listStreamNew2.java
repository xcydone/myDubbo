package com.crossyf.dubbo.springtest.test2.testSet;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class listStreamNew2 {
    public static void main(String[] args) {

        /*------------------------------------CollectionUtils类使用-----------------------------------------*/

        List<Integer> listNum1 = new ArrayList<>();
        listNum1.add(11);
        listNum1.add(22);
        listNum1.add(33);

        List<Integer> listNum2 = new ArrayList<>();
        listNum2.add(33);
        listNum2.add(44);
        listNum2.add(55);

        // 求交集
        Collection collection = CollectionUtils.retainAll(listNum1, listNum2);
        List<Integer> listRetain = new ArrayList<>(CollectionUtils.retainAll(listNum1, listNum2));
        System.out.println(listRetain.toString());

        // 求并集
        List<Integer> listUnion = new ArrayList<>(CollectionUtils.union(listNum1, listNum2));
        System.out.println(listUnion.toString());

        // 差集 listNum1 - listNum2
        List<Integer> listSubtract = new ArrayList<>(CollectionUtils.subtract(listNum1, listNum2));
        System.out.println(listSubtract.toString());

        // 判断两个集合是否相等---哪里会用到这个场景
        System.out.println(CollectionUtils.isEqualCollection(listNum1, listNum2)); // false
        System.out.println(CollectionUtils.isEqualCollection(listRetain, collection)); // true

        /*------------------------------------数组转集合-----------------------------------------*/

        String[] strArr = new String[3];
        strArr[0] = "Hello";
        strArr[1] = "World";
        strArr[2] = "Life";

        // 数组转集合
        List<String> collect = Arrays.stream(strArr).collect(Collectors.toList());
        System.out.println(collect.toString());


        /*------------------------------------equals和hashcode方法重写-----------------------------------------*/

        // equals和hashcode方法重写是为了比较两个对象是否相等（指定条件相等就好，不用全部属性相等）
        EmployeeNew o1 = EmployeeNew.builder().name("张A").age(40).address("东湖").hobbies("4吃饭").build();
        EmployeeNew o2 = EmployeeNew.builder().name("张E").age(40).address("西湖").hobbies("2睡觉").build();
        EmployeeNew o3 = EmployeeNew.builder().name("张A").age(40).address("西湖").hobbies("2睡觉").build();

        System.out.println(o1.equals(o2)); // false
        System.out.println(o1.equals(o3)); // true

        // 没有重写equals和hashcode的方法
        EmployeeNew2 o21 = EmployeeNew2.builder().name("张A").age(40).address("西湖").hobbies("2睡觉").build();
        EmployeeNew2 o22 = EmployeeNew2.builder().name("张A").age(40).address("东湖").hobbies("2睡觉").build();
        EmployeeNew2 o23 = EmployeeNew2.builder().name("张A").age(40).address("东湖").hobbies("2睡觉").build();

        System.out.println(o21.equals(o22)); // false
        System.out.println(o22.equals(o23)); // true

    }
}
