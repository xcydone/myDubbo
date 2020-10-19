package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class charCount {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String strSource = scan.nextLine();
        String strSrc = scan.nextLine();

        int count = getNumFromString(strSource, strSrc);
        System.out.println(count);
    }

    public static int getNumFromString(String strSource, String strSrc){

        if(strSource.length() <= 0) return 0;
        int count = 0;

        String strSo = strSource.toLowerCase();
        String strSr = strSrc.toLowerCase();
        char ch = strSr.charAt(0);

        for (int i = 0; i < strSo.length(); i++) {
            if(ch == strSo.charAt(i)){
                count++;
            }
        }
        return count;
    }

    public static int getNumFromString(String string,char ch){
        int n = 0;
        //将字符串统一转成小写（大写也可）
        String St = string.toLowerCase();
        String st = String.valueOf(ch).toLowerCase();

        //获取一个正则表达式适配器
        Pattern p = Pattern.compile(st);//编译一个正则表达式
        Matcher m = p.matcher(St);//正则表达式适配器
        //查找
        while(m.find()){
            n++;
        }
        return n;

    }
}
