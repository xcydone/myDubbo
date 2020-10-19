package com.crossyf.dubbo.springtest.test.testExam2;

import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[][] scale = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    scale[i][j] = scanner.nextInt();
                }
            }

            List<Integer> lists = new ArrayList<>();
            for (int i = 1; i <= 500; i++) {
                lists.add(maxScale(scale, i));
            }

            Collections.sort(lists, Collections.reverseOrder());
            System.out.println(lists.get(0));
        }
    }

    public static int maxScale(int[][] scale, int num) {

        int xMax = 0;
        int xMin = 0;
        int yMax = 0;
        int yMin = 0;
        int cnt = 0;
        for (int i = 0; i < scale.length; i++) {
            for (int j = 0; j < scale[0].length; j++) {
                if(scale[i][j] == num){
                    cnt++;
                    if(cnt == 1){
                        xMax = i;
                        xMin = i;
                        yMax = j;
                        yMin = j;
                    }

                    if(xMin > i) xMin = i;
                    if(xMax < i) xMax = i;
                    if(yMin > j) yMin = j;
                    if(yMax < j) yMax = j;
                }
            }
        }

        if(cnt <= 1){
            return cnt;
        }else{
            return (xMax == xMin ? 1 : (xMax - xMin + 1))*(yMax == yMin ? 1 : (yMax - yMin + 1));
        }
    }
}
