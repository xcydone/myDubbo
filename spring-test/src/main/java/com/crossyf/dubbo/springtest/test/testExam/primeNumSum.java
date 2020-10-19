package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class primeNumSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Integer src = scanner.nextInt();
            int object = 0;
            int flag = 0;
            for (int i = 2; i < src; i++) {
                if(isPrime(i) && isPrime(src - i)){
                    flag++;
                    if(flag == 1){
                        object = Math.abs(src - 2*i);
                    }else{
                        if(object > Math.abs(src - 2*i)){
                            object = Math.abs(src - 2*i);
                        }
                    }
                }
            }

            System.out.println((src - object)/2);
            System.out.println((src + object)/2);

        }
    }

    /**
     * 是否素数 2 3 5 7
     * @param num
     * @return
     */
    public static Boolean isPrime(Integer num) {
        for (int i = 1; i <= num; i++) {
            if(num%i == 0 && i != 1 && i != num){
                return false;
            }
        }

        return true;
    }
}
