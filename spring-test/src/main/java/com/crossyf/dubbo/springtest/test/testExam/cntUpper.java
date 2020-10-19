package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class cntUpper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String src = scanner.nextLine();
            char[] chars = src.toCharArray();

            int cnt = 0;
            for (int i = 0; i < chars.length; i++) {
                if(chars[i] - 'A' >= 0 && chars[i] - 'A' < 26){
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}
