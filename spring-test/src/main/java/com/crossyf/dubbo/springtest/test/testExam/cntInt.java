package com.crossyf.dubbo.springtest.test.testExam;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 统计正数
 */
public class cntInt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Integer cnt = scanner.nextInt();
            Integer sum = 0;
            Integer countZ = 0;
            Integer countF = 0;
            for (int i = 0; i < cnt; i++) {
                Integer num = scanner.nextInt();
                if(num > 0){
                    sum+=num;
                    countZ++;
                }else if(num < 0){
                    countF++;
                }
            }

            System.out.println(countF + " " + BigDecimal.valueOf(sum*1.0/countZ).setScale(1,BigDecimal.ROUND_HALF_UP));
        }
    }

}
