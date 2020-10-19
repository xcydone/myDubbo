package com.crossyf.dubbo.springtest.test.testExam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class sortStrings {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int num = Integer.parseInt(scan.nextLine());
            List<String> list = new ArrayList<>();
            if (num > 0) {
                // 存数据
                for (int i = 0; i < num; i++) {
                    list.add(scan.nextLine());
                }
            }
            // 排序
            Collections.sort(list);

            // 打印
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }

    }
}
