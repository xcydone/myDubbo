package com.crossyf.dubbo.springtest.test2.testSet;

import java.util.Comparator;
import java.util.PriorityQueue;

public class testQueue {

    public static void main(String[] args) {

        // 按照队列的元素大小进行排列
        // 添加元素： 一般用offer 队列满了返回false不会抛出异常， add队列满了之后会抛出IllegalStateException异常
        /*Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer e1, Integer e2) {
                return e2 - e1;
            }
        };
        Comparator<Integer> cmp = (e1, e2) -> e1 - e2; // 升序排列
        */
        EmployeeNew o1 = EmployeeNew.builder().name("张A").age(40).address("东湖").hobbies("4吃饭").build();
        EmployeeNew o2 = EmployeeNew.builder().name("张E").age(41).address("西湖").hobbies("2睡觉").build();

        PriorityQueue<EmployeeNew> priorityQueue1 = new PriorityQueue<EmployeeNew>(1, Comparator.comparing(EmployeeNew::getAge,Comparator.reverseOrder())); // 无边容器会自动扩容

        priorityQueue1.offer(o1);
        priorityQueue1.offer(o2);

        while(!priorityQueue1.isEmpty()){
            System.out.println(priorityQueue1.poll().toString());
        }




    }

}
