package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;
import java.util.stream.Collectors;

public class countChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String str = scanner.nextLine();
            char[] chars = str.toCharArray();

            // 默认按照key的字典顺序排序
            Map<Character ,Integer> map = new TreeMap<>();
            for(char c: chars){
                if(map.containsKey(c)){
                    map.put(c, map.get(c)+1);
                }else{
                    map.put(c, 1);
                }
            }

            List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
            Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

            for(Map.Entry<Character, Integer> mapping:list){
                System.out.print(mapping.getKey());
            }
            System.out.println();
        }
    }
}
