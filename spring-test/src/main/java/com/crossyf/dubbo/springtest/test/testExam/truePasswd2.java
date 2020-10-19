package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class truePasswd2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String passwd = scanner.nextLine();
            char[] chs = passwd.toCharArray();
            System.out.println(Encrypt(chs));

            String passwd2 = scanner.nextLine();
            char[] chs2 = passwd2.toCharArray();
            System.out.println(unEncrypt(chs2));
        }
    }

    /**
     * 加密
     * @param chs
     * @return
     */
    public static String Encrypt(char[] chs){
        StringBuffer stringBuffer = new StringBuffer("");

        for (char ch: chs){
            if(Character.isDigit(ch)){
                if(ch == '9'){
                    stringBuffer.append('0');
                }else{
                    stringBuffer.append((char)(ch+1));
                }
            }

            if(ch >= 'a' && ch <= 'z') {
                stringBuffer.append(ch == 'z' ? 'A' : (char)(ch-32+1));
            }

            if(ch >= 'A' && ch <= 'Z') {
                stringBuffer.append(ch == 'Z' ? 'a' : (char)(ch+32+1));
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 解密
     * @param chs
     * @return
     */
    public static String unEncrypt (char[] chs){
        StringBuffer stringBuffer = new StringBuffer("");

        for (char ch: chs){
            if(Character.isDigit(ch)){
                if(ch == '0'){
                    stringBuffer.append('9');
                }else{
                    stringBuffer.append((char)(ch-1));
                }
            }

            if(ch >= 'a' && ch <= 'z') {
                stringBuffer.append(ch == 'a' ? 'Z' : (char)(ch-32-1));
            }

            if(ch >= 'A' && ch <= 'Z') {
                stringBuffer.append(ch == 'A' ? 'z' : (char)(ch+32-1));
            }
        }
        return stringBuffer.toString();
    }
}
