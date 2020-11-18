package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class testPrime3 {
    // 最大公约数
    static int gcd(int a, int b) {
        int t;
        if (a < b) {
            t = a;
            a = b;
            b = t;
        }
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // 最小公倍数
    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String args[]) {
        int[] data = new int[100];
        int i, j, k;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }
        j = data[0];
        for (i = 1; i < n; ++i)
            j = gcd(j, data[i]);

        k = 1;
        for (i = 0; i < n; ++i)
            k *= data[i] / j;

        k *= j;
        System.out.println("最大公约数是：" + j);
        System.out.println("最小公倍数是：" + k);
    }
}
