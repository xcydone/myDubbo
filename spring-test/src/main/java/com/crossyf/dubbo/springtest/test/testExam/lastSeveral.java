package com.crossyf.dubbo.springtest.test.testExam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class lastSeveral {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Integer num = scanner.nextInt();
            Integer cnt = scanner.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                Integer sub = scanner.nextInt();
                list.add(sub);
            }

            Collections.sort(list);
            for (int i = 0; i < cnt; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
    }
}
