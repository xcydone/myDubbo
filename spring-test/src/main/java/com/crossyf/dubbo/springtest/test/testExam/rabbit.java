package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class rabbit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int month = scanner.nextInt();
            System.out.println(getTotalCount(month));
        }
    }

    /*
    第n个月的兔子数量由两部分组成，一部分是上个月的兔子f(n-1)，另一部是满足3个月大的兔子，会生一只兔子f(n-2)。
    所以第n个月兔子总数： f(n) = f(n - 1) + f(n - 2)。
     */
    public static int getTotalCount(int month) {
        if(month < 3){
            return 1;
        }
        return getTotalCount(month-1) + getTotalCount(month-2);
    }
}
