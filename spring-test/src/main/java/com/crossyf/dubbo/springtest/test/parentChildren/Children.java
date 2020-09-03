package com.crossyf.dubbo.springtest.test.parentChildren;

public class Children extends Parent{

    public Children(String name, String age){
        super(name, age);
        System.out.println("children constructor method");
        /*staticHH();*/
    }

    static{
        System.out.println("children static code");
    }
    //非静态代码块
    {
        System.out.println("children nonStatic code");
    }

    public static void staticHH(){
        System.out.println("children static hh method");
    }

    // 子类继承重写的方法
    public void hh(){
        System.out.println("children hh method");
    }

    // 子类自己的方法（父类不能调用）
    public void childFun(){
        System.out.println("childFun method");
    }
}