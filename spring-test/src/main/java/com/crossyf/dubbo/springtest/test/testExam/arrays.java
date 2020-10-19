package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class arrays {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            int num = scan.nextInt();
            int start = 1;
            for (int i = 1; i <= num; i++) {
                System.out.print(start + " ");
                int tmp = start;
                for (int j = i+1; j <= num; j++) {
                    tmp += j;
                    System.out.print(tmp + " ");
                }
                System.out.println();
                start = start + i;  // 首位间隔 1 2 3
            }
        }
    }
}
