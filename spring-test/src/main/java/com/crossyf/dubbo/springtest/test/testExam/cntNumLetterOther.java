package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class cntNumLetterOther {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String strSrc = scanner.nextLine();
            char[] chs = strSrc.toCharArray();
            int cntNum = 0;
            int cntLetter = 0;
            int cntOther = 0;
            int cntSpace = 0;

            for (char ch: chs){
                if(ch >= '0' && ch <='9'){
                    cntNum++;
                }else if((ch >= 'a' && ch <='z') || (ch >= 'A' && ch <='Z')){
                    cntLetter++;
                }else if(ch == ' '){
                    cntSpace++;
                }else {
                    cntOther++;
                }
            }

            System.out.println(cntLetter);
            System.out.println(cntSpace);
            System.out.println(cntNum);
            System.out.println(cntOther);
        }
    }
}
