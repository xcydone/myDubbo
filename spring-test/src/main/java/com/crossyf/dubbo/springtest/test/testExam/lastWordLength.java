package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class lastWordLength {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();

        String[] strArr = str.split(" ");
        System.out.println(strArr[strArr.length - 1].length());


    }
}
