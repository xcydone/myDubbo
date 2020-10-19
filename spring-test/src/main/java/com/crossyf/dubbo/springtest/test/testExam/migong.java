package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class migong {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int[][] arrs = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    arrs[i][j] = scanner.nextInt();
                }
            }

            shortest(arrs);
        }
    }

    /**
     * 查找最短路径
     * @param away
     */
    public static void shortest(int[][] away) {

    }
}
