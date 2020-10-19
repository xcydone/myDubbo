package com.crossyf.dubbo.springtest.test.testExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class drinkCoCo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> ls = new ArrayList<>();
        while(scanner.hasNext()){
            Integer num = scanner.nextInt();
            if(num == 0) break;

            ls.add(num);
        }

        for (Integer ll: ls) {
            System.out.println(drink(ll));
        }
    }

    /**
     * 空瓶子喝水
     * @param num
     * @return
     */
    public static Integer drink(Integer num) {
        if(num <= 1) return 0;
        if(num == 2) return 1;
        return drink(num - 2) + 1;
    }
}
