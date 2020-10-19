package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class matchReverseString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String src = scanner.nextLine();

            int cnt = 0;
            for (int i = 0; i < src.length(); i++) {
                for (int j = i + 1; j < src.length(); j++) {
                    String sub = src.substring(i,j);
                    StringBuffer stringBuffer = new StringBuffer(sub);
                    String reverStr = stringBuffer.reverse().toString();
                    String objectStr = sub + reverStr;

                    if(src.contains(objectStr)){
                        int tmp = objectStr.length();
                        if(cnt < tmp){
                            cnt = tmp;
                        }
                    }
                }
            }

            System.out.println(cnt);
        }
    }
}
