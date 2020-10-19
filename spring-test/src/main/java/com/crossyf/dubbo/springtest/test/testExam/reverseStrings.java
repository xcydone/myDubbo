package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class reverseStrings {
    public static void main(String[] args) {
        Scanner snn = new Scanner(System.in);
        String str = snn.nextLine();
        System.out.println(reverse(str));
    }

    public static String reverse(String sentence){
        /*String[] strs = sentence.split(" ");*/
        String[] strs = sentence.split("[^a-zA-Z]+");

        StringBuilder sb=new StringBuilder();
        for(int i = 0; i < strs.length; i++){
            sb.append(strs[strs.length-i-1]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
