package com.crossyf.dubbo.springtest.test.testExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class likeSeven {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            int num = scanner.nextInt();
            int cnt = 0;

            List<Integer> ls = new ArrayList<>();
            for (int i = 1; i <= num; i++) {
                if(i%7 == 0){
                    ls.add(i);
                    cnt++;
                }else if(String.valueOf(i).contains("7")){
                    ls.add(i);
                    cnt++;
                }
            }
            System.out.println(ls.toString());
            System.out.println(cnt);
        }
    }
}
