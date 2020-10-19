package com.crossyf.dubbo.springtest.test.testExam2;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String strSrc = scanner.next();
            System.out.println(cntP(strSrc));
        }
    }

    public static Integer cntP(String strSrc) {
        String tmpStr = strSrc.replaceAll(",","");
        String[] arrs = tmpStr.split("[0]+");
        int cnt = 0;
        for(String str: arrs){
            if(str.length() <= 3){
                cnt = cnt + 1;
            }else{
                cnt = cnt + Integer.parseInt(str)/3;
                if(Integer.parseInt(str) % 3 != 0){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
