package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class eightWord {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()){
            String str = scan.nextLine();
            if(str.length() % 8 != 0){
                str = str + "00000000";
            }

            while(str.length() >= 8) {
                System.out.println(str.substring(0, 8));
                str = str.substring(8);
            }
        }
    }
}
