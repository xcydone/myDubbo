package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class passwdvalide {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String passwd = scanner.nextLine();
            char[] chs = passwd.toCharArray();
            if(passwd.length() > 8 && isCharacter(chs) && !isRepeat(passwd)){
                System.out.println("OK");
            }else{
                System.out.println("NG");
            }
        }
    }

    public static Boolean isCharacter(char[] chs) {
        int isUpperLetter = 0;
        int isLowerLetter = 0;
        int isDigit = 0;
        int isOther = 0;

        for (char ch: chs){
            if(ch - 'A' >= 0 && ch - 'A' <=25){
                isUpperLetter = 1;
            } else if(ch - 'a' >= 0 && ch - 'a' <=25) {
                isLowerLetter = 1;
            } else if(Character.isDigit(ch)) {
                isDigit = 1;
            }else{
                isOther = 1;
            }
        }

        if(isUpperLetter + isLowerLetter + isDigit + isOther >=3){
            return true;
        }
        return false;
    }

    public static Boolean isRepeat(String passwd) {
        for (int i = 0; i < passwd.length(); i++) {
            String str = passwd.substring(i,i+3); // 判断最小的有3个连续的重复就可以了
            String obj = passwd.substring(i+1);

            if(obj.contains(str)){
                return true;
            }
        }

        return false;
    }
}

/*for (int i = 0; i < passwd.length(); i++) {
    for (int j = i+1; j < passwd.length(); j++) {
        String str = passwd.substring(i,j);
        String obj = passwd.substring(j);
        if(j-i >= 3 && obj.contains(str)){
            return true;
        }
    }
}*/
