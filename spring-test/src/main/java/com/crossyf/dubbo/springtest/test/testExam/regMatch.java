package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class regMatch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String zhengze = sc.next();
            String s = sc.next();
            // 英文字母和数字0-9是 \w

            zhengze = zhengze.replaceAll("\\?","[\\\\w]{1}");
            zhengze = zhengze.replaceAll("\\*","[\\\\w]*");
            System.out.println(s.matches(zhengze));
        }
        sc.close();
    }
}
