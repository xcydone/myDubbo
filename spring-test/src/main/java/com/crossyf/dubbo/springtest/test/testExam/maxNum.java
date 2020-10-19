package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class maxNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String src = scanner.nextLine();
            String srcDeal = src.replaceAll("[b-zA-Z]","a");
            String[] objects = srcDeal.split("a");

            int length = 0;
            String strObject = "";
            for (String object: objects){
                if(length < object.length()){
                    length = object.length();
                    strObject = object;
                }
                // 字符串长度相等合并输出
                else if(length == object.length()){
                    strObject += object;
                }
            }

            System.out.println(strObject + "," + length);
        }
    }
}
