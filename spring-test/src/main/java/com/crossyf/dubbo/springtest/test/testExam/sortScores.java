package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;
import java.util.stream.Collectors;

public class sortScores {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Integer sortNum = scanner.nextInt();
            Integer sortFlag = scanner.nextInt();

            Map<String, Integer> map = new LinkedHashMap<>();
            for (int i = 0; i < sortNum; i++) {
                String name = scanner.next();
                Integer score = scanner.nextInt();
                map.put(name + " " + score, score);
            }

            List<Integer> ls = map.values().stream().distinct().collect(Collectors.toList());
            if(sortFlag == 0){
                Collections.sort(ls, Collections.reverseOrder());
            }else{
                Collections.sort(ls);
            }

            for (int i = 0; i < ls.size(); i++) {
                Iterator it = map.keySet().iterator();
                while(it.hasNext()){
                    String key = (String)it.next();
                    if (map.get(key) == ls.get(i)) {
                        System.out.println(key);
                    }
                }
            }
        }
    }
}
