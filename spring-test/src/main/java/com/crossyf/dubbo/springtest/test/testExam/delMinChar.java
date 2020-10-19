package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;

public class delMinChar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String strSource = scanner.nextLine();
            char[] chs = strSource.toCharArray();

            // 统计字符出现的个数
            HashMap<Character, Integer> map = new LinkedHashMap<>();
            for(char ch: chs){
                if(map.containsKey(ch)){
                    map.put(ch, map.get(ch) + 1);
                }else{
                    map.put(ch, 1);
                }
            }

            // 获取value的最小值
            Collection<Integer> values = map.values();
            int value = Collections.min(values);

            StringBuffer sb=new StringBuffer("");
            for(char ch: chs){
                if(map.get(ch) != value)
                    sb.append(ch);
            }

            System.out.println(sb.toString());
        }
    }

}
