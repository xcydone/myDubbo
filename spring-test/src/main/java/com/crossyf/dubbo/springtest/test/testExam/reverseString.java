package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class reverseString {
    public static String stringReverse(String str){
        // 字符串是不可变的，需要用StringBuffer的reverse方法
        StringBuffer stringBuffer = new StringBuffer(str);
        return stringBuffer.reverse().toString();
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.print(stringReverse(str));
    }
}
