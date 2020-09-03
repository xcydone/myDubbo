package com.crossyf.dubbo.springtest.test.parentChildren;

public class testParentChildren {
    public static void main(String[] args){

        testParentAndChildren();
    }

    public static  void testParentAndChildren() {
        /*Parent pp=new Parent();
        System.out.println("**********");
        pp.hh();
        System.out.println("**********");*/

        Parent pc=new Children("child1","12");
        System.out.println("**********");
        pc.hh();  // pc调不到child的方法
        System.out.println("**********");

        /*Children cc = new Children("child1","12");
        System.out.println("**********");
        cc.hh();
        *//*cc.name = "";*//*
        cc.parentFun();
        cc.childFun();
        System.out.println("**********");*/

        /*Children cp = new Parent();
        System.out.println("**********");*/

    }

}
