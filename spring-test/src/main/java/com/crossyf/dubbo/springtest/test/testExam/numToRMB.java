package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class numToRMB {

    // 个位
    public static String[] num1 = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    // 十位
    public static String[] num2 = { "拾", "佰", "仟", "万","拾", "佰", "仟", "亿","拾", "佰", "仟", "亿"};

    // 小数位
    public static String[] num3 = { "元", "角", "分"};

    public static String[] num4 = { "整" };

    public static Long[] num5 = {10L, 100L, 1000L, 10000L, 100000000L};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String str = scanner.next();
            String[] strs = str.split("\\.");

            //整数处理
            StringBuffer stringBuffer = new StringBuffer("");
            dealInt(strs[0]);
            /*if(strs.length > 1){
                stringBuffer.append(dealDou(strs[1]));
            }*/

            System.out.println(stringBuffer.toString() + num3[0]);
        }
    }
    public static void dealInt(String intSrc) {
        /*List<Character> list = intSrc.chars().mapToObj(ch -> (char)ch).collect(Collectors.toList());*/

        StringBuffer stringBuffer = new StringBuffer("");
        char[] chs = intSrc.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            int num = Integer.parseInt(String.valueOf(chs[chs.length - i - 1]));
            stringBuffer.append(num1[num] + num2[i]);
        }

        System.out.println(stringBuffer.reverse().toString());



        /*if(intSrc < 10) return num1[intSrc.intValue()];

        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < String.valueOf(intSrc).length() && intSrc/num5[i] != 0; i++) {
            System.out.println(stringBuffer);
            stringBuffer.append(dealInt(intSrc/num5[i]) + num2[i] + dealInt(intSrc%num5[i]));
        }

        return stringBuffer.toString();*/
    }
}
