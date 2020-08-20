package com.crossyf.dubbo.springtest.test.parentChildren;

public class Parent {
    public Parent(){
        System.out.println("parent constructor method");
        // 静态方法在类内部被调用
        /*staticHH();*/
    }

    // 静态代码块是在类加载时自动执行的，只执行一次
    static{
        System.out.println("parent static code");
    }

    //非静态代码块 在创建对象自动执行的代码,不创建对象不执行该类的非静态代码块
    {
        System.out.println("parent nonStatic code");
    }
    public static void staticHH(){
        System.out.println("parent static hh");
    }
    public void hh(){
        System.out.println("parent hh");
    }
}
