package com.crossyf.dubbo.springtest.test.testExam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class mergeStrings {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            String str1 = scanner.next();
            String str2 = scanner.next();
            System.out.println(processString(str1, str2));
        }

    }

    public static String processString(String str1, String str2) {
        String strSource = str1 + str2;
        if(strSource == null || strSource.length() == 0){
            return "";
        }

        char[] chs = strSource.toCharArray();

        List<Character> odds = new ArrayList<>();
        List<Character> evens = new ArrayList<>();

        // 存值
        for (int i = 0; i < chs.length; i++) {
            if(i%2 == 0){
                evens.add(chs[i]);
            }else{
                odds.add(chs[i]);
            }
        }

        // 排序
        Collections.sort(odds);
        Collections.sort(evens);

        // 合并恢复
        char[] oddAndEven = new char[chs.length];
        int oddIndex = 0;
        int evenIndex = 0;
        for (int i = 0; i < chs.length; i++) {
            if(i%2 == 0){
                oddAndEven[i] = evens.get(evenIndex++);
            }else{
                oddAndEven[i] = odds.get(oddIndex++);
            }
        }

        // 字符处理
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < oddAndEven.length; i++){
            char ch = oddAndEven[i];
            if((ch >= '0' && ch <= '9') || (ch>= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F')){
                stringBuffer.append(processChar(ch)); // 反转，小写转大写
            }else{
                stringBuffer.append(ch);
            }
        }

        return stringBuffer.toString();
    }

    private static char processChar(char c){   // 16-10
        int num = 0;
        if(c >= '0' && c <= '9'){
            num = Integer.parseInt(c+"");
        }else if(c >= 'a' && c <= 'f'){ // a-f16进制 a-97
            num = c - 87;
        }else { // A-F代表16进制  A-65
            num = c - 55;
        }
        return getReverseResult(num);//也就是对该十六进制数字进行处理
    }

    private static char getReverseResult(int num){

        // 10-2
        /*StringBuffer stringBuffer = new StringBuffer(Integer.toBinaryString(num));
        String nums = stringBuffer.reverse().toString();*/

        String nums = reverseBinaryString(num);

        // 2-10
        int res = Integer.parseInt(nums,2); //二进制字符串转十进制数字

        // 10-16
        if(res >=0 && res <= 9){
            return (res+"").charAt(0);
        }else{
            return (char)(res+55);  // 数字转大写字母 + 55
        }
    }

    private static String reverseBinaryString(int num){
        int k = 1 << 3; // 1000
        StringBuffer sb = new StringBuffer();
        for(int i=0; i < 4; i++){
            int flag = ((num&k)==0 ? 0: 1);  // 按位与
            sb.append(flag);
            num = num << 1;
        }
        return sb.reverse().toString();
    }

}
