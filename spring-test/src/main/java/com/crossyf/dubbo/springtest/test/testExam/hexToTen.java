package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class hexToTen {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()){
            String str = scan.nextLine();
            Integer tenNum = Integer.parseInt(str.substring(2), 16);
            System.out.println(tenNum);
        }
    }
}
