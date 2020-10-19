package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class truePasswd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String passwd = scanner.nextLine();
            char[] chs = passwd.toCharArray();
            StringBuffer stringBuffer = new StringBuffer("");

            for (char ch: chs){
                if(Character.isDigit(ch)){
                    stringBuffer.append(ch);
                }

                if(ch >= 'a' && ch <= 'z') {
                    if (ch == 's' || ch == 'v'|| ch == 'y' || ch == 'z')
                        stringBuffer.append((ch - 'a') / 3 + 1);
                    else
                        stringBuffer.append((ch - 'a') / 3 + 2);
                }

                if(ch >= 'A' && ch <= 'Z') {
                    stringBuffer.append(ch == 'Z' ? 'a' : (char)(ch+32+1));
                }
            }
            System.out.println(stringBuffer.toString());
        }
    }
}
