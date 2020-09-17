package com.crossyf.dubbo.springtest.test.testSort;

public class TestArray {
    public static void main(String[] args) {
        double[] myList = {1.9, 2.9, 3.4, 3.5};

        // 打印所有数组元素
        for (double element: myList) {
            System.out.println(element);
        }

        double max = myList[0];
        for (int i = 1; i < myList.length; i++) {
            if (myList[i] > max) max = myList[i];
        }
        System.out.println("Max is " + max);

        double max2 = myList[0];
        for (double element: myList) {
            if (element > max2) max2 = element;
        }
        System.out.println("Max is " + max2);
    }
}
