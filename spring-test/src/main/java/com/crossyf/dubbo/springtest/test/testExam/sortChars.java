package com.crossyf.dubbo.springtest.test.testExam;

/*import org.apache.commons.lang3.ArrayUtils;*/

import java.util.*;

public class sortChars {

    public static void main(String[] args) {
        /*Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String strSource = scan.nextLine();
            char[] chs = strSource.toCharArray();
            Arrays.sort(chs);
            System.out.print(chs);
        }*/
        test();
    }

    public static void test() {
        // 数组初始化
        char[] chs = new char[]{'1','2'};
        char[] chs2 = {'1','2'};
        char[] chs3 = new char[2];

        System.out.println(chs); // 数组可以直接打印
    }
}

/*// 转换list
Character[] chs = ArrayUtils.toObject(strSource.toCharArray());
List<Character> lc = Arrays.asList(chs);

// 排序
Collections.sort(lc);*/
