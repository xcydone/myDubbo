package com.crossyf.dubbo.springtest.test.parentChildren;

public class Parent {

    // 父类public属性子类可用
    public String name;

    // 父类public属性子类不可用
    private String age;

    public Parent(String name, String age){
        this.name = name;
        this.age = age;

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
        System.out.println("parent static method");
    }

    // 子类继承重写的方法
    public void hh(){
        System.out.println("parent hh method");
    }

    // 父类自己的方法（子类可用） -- public
    public void parentFun(){
        System.out.println("parentPublicFun method");
    }

    // 父类自己的方法（子类不可用） -- private
    private void parentFun(String name){
        System.out.println("parentPrivateFun method");
    }
}









