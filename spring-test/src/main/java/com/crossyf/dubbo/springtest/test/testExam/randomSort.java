package com.crossyf.dubbo.springtest.test.testExam;

import java.io.IOError;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class randomSort {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        // 理解一下题目的意思，只是处理了一组数据
        /*while(sc.hasNext()){

            Set<Integer> set=new TreeSet<>();
            int n=sc.nextInt();
            if(n>0){
                for(int i=0;i<n;i++){
                    set.add(sc.nextInt());
                }
            }
            for(Integer i:set){
                System.out.println(i);
            }
        }*/


        // 想处理多组数据的话需要接受多组输入--输入结束存在问题
        while(sc.hasNextInt()){
            List<Integer> arrSource = new ArrayList<>();
            int num = sc.nextInt();

            //获取数据
            if(num > 0){
                for(int i=0; i < num; i++){
                    arrSource.add(sc.nextInt());
                }
            }

            // 去重
            List result = arrSource.stream().distinct().collect(Collectors.toList());

            // 排序
            Collections.sort(result);

            //打印
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }

        }

        /*int num = 0;
        for (int i = 0; i < arrSource.size(); i += num) {
            for (int j = 1; j < arrSource.get(i) + 1; j++) {
                arrDest.add(arrSource.get(i + j));
            }
            num = arrSource.get(i) + 1;
        }*/
    }
}
