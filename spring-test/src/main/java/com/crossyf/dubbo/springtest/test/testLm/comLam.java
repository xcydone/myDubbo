package com.crossyf.dubbo.springtest.test.testLm;

import com.crossyf.dubbo.springtest.test.testListStream.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class comLam {
    public static void main(String[] args) {

        Employee o1 = new Employee().setName("张C").setAge(30);
        Employee o2 = new Employee().setName("张D").setAge(40);

        List<Employee> em = new ArrayList<>(); // 有序可重复
        em.add(o2);
        em.add(o1);
        System.out.println(Arrays.asList(em));

        // 原始写法
        Comparator<Employee> cmp00 = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getAge() - o2.getAge();
            }
        };

        // 1.8写法
        Comparator<Integer> cmpInt = (x,y) -> Integer.compare(x,y);
        Comparator<Integer> cmpInt2 = (x,y) -> x-y;
        Comparator<Employee> cmpEmp = (e1, e2) -> {return e1.getAge() - e2.getAge();};
        Comparator<Employee> cmpEmp2 = (e1, e2) -> e1.getAge() - e2.getAge();

        /*Collections.sort(em, cmpEmp2);
        Collections.sort(em, Comparator.comparingInt(Employee::getAge));*/
        Collections.sort(em, Comparator.comparing(Employee::getName));
        System.out.println(Arrays.asList(em));  // 转换为数组

        List<Employee> emNew = em.stream().sorted(Comparator.comparing(Employee::getAge, Comparator.reverseOrder())).collect(Collectors.toList());

        System.out.println(Arrays.asList(em));
        System.out.println(Arrays.asList(emNew));


        /*// 判断字符串是否内容是否相等
        String str = "Hello";
        System.out.println("hello".equals(str));
        System.out.println("hello".equalsIgnoreCase(str));

        // 判断字符串是否为空 常量置于前面
        String tmpNull = null;
        String tmp = " ";

        System.out.println("hello".equals(tmpNull));
        // System.out.println(tmpNull.equals("hello")); // 会报空指针异常
        System.out.println("".equals(tmp.trim())); // true


        System.out.println(Objects.isNull(tmpNull));  // true
        System.out.println(Objects.isNull(tmp)); // false

        System.out.println(Optional.ofNullable(tmpNull).isPresent()); // false
        System.out.println(Optional.ofNullable(tmp.trim()).isPresent()); // true


        // 方法引用
        List<String> names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);

        // 基本的类比较
        Collections.sort(names);

        // 封装类比较
        Collections.sort(em, Comparator.comparing(Employee::getName));*/


    }
}
