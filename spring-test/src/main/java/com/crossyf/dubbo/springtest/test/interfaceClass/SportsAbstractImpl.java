package com.crossyf.dubbo.springtest.test.interfaceClass;

public abstract class SportsAbstractImpl implements Sports{
    // 抽象类自己的属性和方法
    private String name;

    // 抽象类的构造
    public void SportsAbstractImpl(String name){
        this.name = name;
    }

    // 抽象类自己的方法
    public void SportsAbstractImplFun(){
        System.out.println("SportsAbstractImpl Class SportsAbstractImplFun method");
    }

    // 抽象类自己的抽象方法
    public abstract void eat();

    // 抽象类不用实现接口的方法，但是继承抽象类的类需要实现
}
