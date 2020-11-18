package com.crossyf.dubbo.springtest.test.testExam;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testPrime {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int num = 4; num <= 100 ; num ++) {
            for (int i = 2; i < num; i++) {
                if(isPrime(i) && isPrime(num - i)){
                    list.add(num);
                    break;
                }
            }
        }

        System.out.println(Arrays.asList(list));
        System.out.println(list.size());
    }

    /**
     * 判断是否素数 1和它本身
     * @param num
     * @return
     */
    public static Boolean isPrime(Integer num) {
        if(num < 2){
            return false;
        }

        for (int i = 2; i < num; i++) {
            if(num%i == 0){
                return false;
            }
        }

        return true;
    }
}
