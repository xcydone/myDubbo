package com.crossyf.dubbo.springtest.test.testExam2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String strSrc = scanner.next();
            char[] chs = strSrc.toCharArray();

            System.out.println(cntOLetter(strSrc));
        }
    }

    public static Integer cntOLetter(String strSrc) {

        // 判断包含o字符的个数
        char[] chs = strSrc.toCharArray();
        int cnt = 0;
        for(char ch: chs){
            if(ch == 'o'){
                cnt++;
            }
        }

        if(cnt%2 == 0){
            return chs.length;
        } else{
            return chs.length - 1;
        }
    }
}
