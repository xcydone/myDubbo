package com.crossyf.dubbo.springtest.test.testType;

public class testType {
    public static void main(String[] args) {
        String str = "hello";
        System.out.println(str instanceof String);

        switch (str){
            case "Hello":
                System.out.println("我是大写的");
                break;
            case "hello":
                System.out.println("我是小写的");
                break;
            default:
                System.out.println("啥也不是");
        }


        String[] strArr = {"0", "0.0", "0.00"};
        Float num = 0f;
        Double num2 = 0d;
        String str1 = String.valueOf(num);
        String str2 = String.valueOf(num2);

        System.out.println(str1);  // double和float转string都是0.0
        System.out.println(str2);

        System.out.println(str1.equals(strArr[0])); // false
        System.out.println(str1.equals(strArr[1])); // true
        System.out.println(str1.equals(strArr[2])); // false

        System.out.println(str2.equals(strArr[0])); // false
        System.out.println(str2.equals(strArr[1])); // true
        System.out.println(str2.equals(strArr[2])); // false

        System.out.println(num==0);

        int a = '1';
        System.out.println(a);
    }
}
