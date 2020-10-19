package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class deseveApple {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Integer apples = scanner.nextInt();
            Integer drawer = scanner.nextInt();

            System.out.println(distributeApps(apples, drawer));

        }
    }

    public static Integer distributeApps(Integer apples, Integer drawer) {
        if(apples == 0 || drawer == 1){
            return 1;
        }

        if(apples < drawer){
            return distributeApps(apples, apples);
        }else {
            return distributeApps(apples, drawer - 1) + distributeApps(apples - drawer, drawer);
        }

    }
}
