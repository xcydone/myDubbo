package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;

public class mergeMap {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            int num = scan.nextInt();
            // 只能用treeMap才能编译通过，LinkedHashMap用例通过为0
            Map<Integer, Integer> map = new TreeMap<>();
            if(num > 0){
                for (int i = 0; i < num; i++) {
                    // nextint方法
                    int key = scan.nextInt();
                    int value = scan.nextInt();
                    if(map.containsKey(key)){
                        map.put(key, map.get(key) + value);
                    }else{
                        map.put(key, value);
                    }
                }
            }

            for (Integer key : map.keySet()) {
                System.out.println(key + " " + map.get(key));
            }

            /*
            // 不要用字符串去分隔就用 nextInt方法去获取
            int num = Integer.parseInt(scan.nextLine());
            Map<String, String> map = new HashMap<>();
            if(num > 0){
                for (int i = 0; i < num; i++) {
                    String source = scan.nextLine();
                    String[] keyAndValue = source.split(" ");
                    if(map.containsKey(keyAndValue[0])){
                        Integer result = Integer.parseInt(map.get(keyAndValue[0])) + Integer.parseInt(keyAndValue[1]);
                        map.put(keyAndValue[0], result + "");
                    }else{
                        map.put(keyAndValue[0],keyAndValue[1]);
                    }
                }
            }

            for (String key : map.keySet()) {
                System.out.println(key + " " + map.get(key));
            }*/

        }
    }
}