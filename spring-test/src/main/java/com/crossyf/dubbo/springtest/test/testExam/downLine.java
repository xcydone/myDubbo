package com.crossyf.dubbo.springtest.test.testExam;

import java.math.BigDecimal;
import java.util.Scanner;

public class downLine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int start = scanner.nextInt();
            int turn = 5;
            /*int turn = scanner.nextInt();*/
            Double sumAway = getTotalLine(Double.valueOf(start), turn);
            System.out.println(BigDecimal.valueOf(sumAway).setScale(6, BigDecimal.ROUND_HALF_UP));
            Double reverseAway = start * Math.pow(0.5, Double.valueOf(turn));
            System.out.println(BigDecimal.valueOf(reverseAway).setScale(6, BigDecimal.ROUND_HALF_UP));
        }
    }

    public static Double getTotalLine(Double start, int turn) {
        if(turn == 1){
            return 1.0;
        }
        Double height = start * 0.5;
        return getTotalLine(height, turn - 1) + height * 2;
    }
}
