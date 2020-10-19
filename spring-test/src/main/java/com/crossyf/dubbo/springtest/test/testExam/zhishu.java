package com.crossyf.dubbo.springtest.test.testExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class zhishu {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            Integer num = scan.nextInt();
            List<Integer> subNums = new ArrayList<>();
            for (int i = 2; i <= num; ) {
                if(num % i == 0){
                    subNums.add(i);
                    num = num / i;
                }else{
                    i++;
                }
            }
            for (int i = 0; i < subNums.size(); i++) {
                System.out.print(subNums.get(i) + " ");
            }
        }
    }
}
