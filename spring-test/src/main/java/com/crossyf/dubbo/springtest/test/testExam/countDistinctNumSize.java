package com.crossyf.dubbo.springtest.test.testExam;

import java.util.HashMap;
import java.util.Scanner;

public class countDistinctNumSize {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String inString = in.nextLine();
        System.out.println(charNum(inString));
    }

    //统计不重复的字符的个数
    public static int charNum(String inString){
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i< inString.length(); i++){
            if(map.containsKey(inString.charAt(i))){
                map.put(inString.charAt(i), map.get(inString.charAt(i)+1));
            }else{
                map.put(inString.charAt(i), 1);
            }
        }
        return map.size();
    }
}
