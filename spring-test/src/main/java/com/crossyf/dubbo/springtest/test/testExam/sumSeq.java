package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class sumSeq {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Integer num = scanner.nextInt();
            System.out.println(sum(num));
        }
    }

    public static Integer sum(Integer num) {
        if(num == 1){
            return 2;
        }
        return sum(num - 1) + 3*(num - 1) + 2;
    }
}
