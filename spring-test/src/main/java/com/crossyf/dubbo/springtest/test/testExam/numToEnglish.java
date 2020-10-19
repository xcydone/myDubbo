package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class numToEnglish {

    // 个位
    public static String[] num1 = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

    // 十几
    public static String[] num2 = { "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen","seventeen", "eighteen", "nineteen" };

    // 十位
    public static String[] num3 = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty","ninety" };

    // 百千百万十亿位
    public static String[] num4 = { "hundred", "thousand", "million", "billion" };

    public static Long[] num5 = { 100L, 1000L, 1000000L, 1000000000L, 1000000000000L};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Long intSrc = scanner.nextLong();
            System.out.println(numToEn(intSrc));
        }
    }
    public static String numToEn(Long intSrc) {
        if(intSrc >= 0 && intSrc <= 9) return num1[intSrc.intValue()];
        if(intSrc >= 10 && intSrc <= 19) return num2[intSrc.intValue()-10];
        if(intSrc >= 20 && intSrc <= 100) return num3[intSrc.intValue()/10-2] + (intSrc%10 == 0 ? "" : " "+ num1[intSrc.intValue()%10]);

        for (int i = 0; i < 4; i++) {
            if(intSrc < num5[i+1])
                return numToEn(intSrc/num5[i]) + " " + num4[i] + (intSrc%num5[i] == 0 ? "" : (i==0 ? " and ":" ") + numToEn(intSrc%num5[i]));
        }

        return "";
    }
}
