package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class perfectNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Integer num = scanner.nextInt();
            int cnt = 0;
            for (int i = 1; i <= num; i++) {
                if(isPerfect(i)){
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }

    public static Boolean isPerfect(Integer num) {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            if(num%i == 0 && i != num){
                sum += sum + i;
            }
        }

        if(sum == num){
            return true;
        }

        return false;
    }
}
