package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;
import java.util.stream.Collectors;

public class beautifulName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            int num = Integer.parseInt(scanner.nextLine());
            if(num > 0){
                int[] sums = new int[num];

                // 存放数据
                for (int i = 0; i < num; i++) {
                    String name = scanner.nextLine();
                    Map<Character,Integer> map = new HashMap<>();

                    for (int j = 0; j < name.length(); j++) {
                        if(map.containsKey(name.charAt(j))){
                            map.put(name.charAt(j), map.get(name.charAt(j)) + 1);
                        }else{
                            map.put(name.charAt(j), 1);
                        }
                    }

                    List<Integer> values = map.values().stream().collect(Collectors.toList());
                    /*Collections.sort(values, Collections.reverseOrder());*/
                    /*Collections.sort(values, (o1, o2) -> o2 - o1);*/
                    Collections.sort(values, new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o2 - o1;
                        }
                    });

                    int start = 26;
                    int sum = 0;
                    for (int k = 0; k < values.size() && start > 0; k++) {
                        sum += values.get(k) * start;
                        start--;
                    }

                    sums[i] = sum;

                }

                for (int i = 0; i < sums.length; i++) {
                    System.out.println(sums[i]);
                }

            }
        }
    }
}
