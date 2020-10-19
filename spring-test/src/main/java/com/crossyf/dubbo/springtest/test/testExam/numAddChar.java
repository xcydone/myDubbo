package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class numAddChar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String str = scanner.nextLine();
            str = str.replaceAll("(\\d+)","\\*$1\\*");
            System.out.println(str);
        }
    }
}

/*
正则表达式中 的$1,$2

$1，$2表达的是小括号里面的内容

$1是第一个小括号里的内容，$2是第二个小括号里面的内容，依此类推

比如(\\d{4})(\\d{2})(\\d{2})  匹配"20190919"

$1是第一个括号里匹配的2019

$2是第二个括号里匹配的09

$3是第三个括号里匹配的19
*/
/*
链接：https://www.nowcoder.com/questionTerminal/637062df51674de8ba464e792d1a0ac6
来源：牛客网

//方法1：stringbuilder
    public static String MarkNum(String pInStr)
    {
        char[] chs = pInStr.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<chs.length;i++){
            if((chs[i]-'0'>=0) && (chs[i]-'0'<=9)){
                sb.append("*"+chs[i]+"*");
            }else {
                sb.append(chs[i]);
            }
        }
        System.out.println(sb.toString().replace("**",""));
        return null;
    }
 */
