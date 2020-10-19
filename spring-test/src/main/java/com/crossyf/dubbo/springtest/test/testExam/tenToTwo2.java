package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class tenToTwo2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()){
            int n = scan.nextInt();
            String result = Integer.toBinaryString(n);

            int cnt = 0;
            String str = "1";
            for (int i = 0; i < result.length(); i++) {
                if(result.contains(str)){
                    str = str + "1";
                }
            }
            System.out.println(str.length()-1);
        }
    }
}
