package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class mathCeil {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            Float ft = scan.nextFloat();

            // 编译不通过
            /*String str = Math.round(ft) + "";
            System.out.println(str.substring(0, str.indexOf(".")));*/
            System.out.println((int)(Math.round(ft)));
        }
    }
}
