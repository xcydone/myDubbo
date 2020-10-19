package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;

public class firstTimeChar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String strSrc = scanner.nextLine();
            Map<Character, Integer> map = new LinkedHashMap<>();
            for (int i = 0; i < strSrc.length(); i++) {
                if(map.containsKey(strSrc.charAt(i))){
                    map.put(strSrc.charAt(i), map.get(strSrc.charAt(i)) + 1);
                }else{
                    map.put(strSrc.charAt(i), 1);
                }
            }
            if(map.size() == 0){
                System.out.println("-1");
            }else{
                int ccn = 0;
                Iterator it = map.keySet().iterator();
                while(it.hasNext()){
                    Character ch = (Character)it.next();
                    if(map.get(ch) == 1){
                        ccn++;
                        System.out.println(ch);
                        break;
                    }
                }
                if(ccn == 0){
                    System.out.println("-1");
                }
            }
        }
    }
}
