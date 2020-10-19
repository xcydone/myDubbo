package com.crossyf.dubbo.springtest.test.testExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class sortStringByChars {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String str = scanner.nextLine();

            // 不能用List 会报错
            /*List<Character> list = new ArrayList<>();*/
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<26; i++){
                char c = (char)(i+'A'); //遍历循环从A开始不分大小写，将字母部分依次写进去
                for(int j=0; j<str.length(); j++){
                    char sc = str.charAt(j);
                    if(c == sc || c == sc -32)//小写大小来匹配
                        /*list.add(sc);*/
                        sb.append(sc);
                }
            }

            // 非字符的处理
            for(int i = 0; i < str.length(); i++){
                char ch = str.charAt(i);
                if( !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z') ){
                    sb.insert(i, ch);
                }
            }

            // 打印
            System.out.println(sb.toString());

            /*for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }*/
        }
    }
}
