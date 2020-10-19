package com.crossyf.dubbo.springtest.test.testExam;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class distinctNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        // 转换为字符串，取各个位置上的字符串
        HashMap<Character, Integer> map = new LinkedHashMap<>();
        String str = String.valueOf(input);
        for(int i = str.length()-1; i >= 0; i--){
            if(!map.containsKey(str.charAt(i))){
                map.put(str.charAt(i), 1);
            }
        }
        for(int j =0 ; j < map.keySet().size(); j++){
            System.out.print(map.keySet().toArray()[j]);
        }
    }
}
