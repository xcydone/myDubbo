package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class AutomorphicNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Integer num = scanner.nextInt();
            int cnt = 0;
            for (int i = 0; i < num; i++) {
                String strPow = String.valueOf(i*i);
                if(strPow.endsWith(String.valueOf(i))){
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}
