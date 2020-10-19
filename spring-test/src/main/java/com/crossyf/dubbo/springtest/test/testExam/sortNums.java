package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;

public class sortNums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Integer num = scanner.nextInt();
            List<Integer> ls = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                ls.add(scanner.nextInt());
            }
            Integer sortFlag = scanner.nextInt();

            if(sortFlag == 0){
                Collections.sort(ls);
            }else{
                Collections.sort(ls, Collections.reverseOrder());
            }

            for (int i = 0; i < ls.size(); i++) {
                System.out.print(ls.get(i) +" ");
            }

            System.out.println();
        }
    }
}
