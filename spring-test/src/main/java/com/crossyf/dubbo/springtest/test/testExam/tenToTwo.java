package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class tenToTwo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()){
            int n = scan.nextInt();
            String result = Integer.toBinaryString(n);

            int cnt = 0;
            for (int i = 0; i < result.length(); i++) {
                if(result.charAt(i) == '1'){
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}
