package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class reverseNum {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        // 字符串是不可变的，需要用StringBuffer的reverse方法
        StringBuffer sb = new StringBuffer(String.valueOf(input));
        String st = sb.reverse().toString();

        System.out.println(st);
    }
}
