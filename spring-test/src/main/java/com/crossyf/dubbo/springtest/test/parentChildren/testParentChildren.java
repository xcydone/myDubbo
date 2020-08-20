package com.crossyf.dubbo.springtest.test.parentChildren;

public class testParentChildren {
    public static void main(String[] args){
        String str = "abc";
        System.out.print(str.codePointAt(0));

        char data[] = {'a', 'b', 'c'};
        char[] data2 = {'a', 'b', 'c'};
        String dtt = new String(data);
        String dtt2 = new String(data2);

        /*testParentAndChildren();*/
    }

    public static  void testParentAndChildren() {
        /*Parent pc=new Children();
        System.out.println("**********");
        pc.hh();
        System.out.println("**********");*/

        /*Parent pp=new Parent();
        System.out.println("**********");
        pp.hh();
        System.out.println("**********"); */

        Children cc = new Children();
        System.out.println("**********");
        cc.hh();
        System.out.println("**********");

        /*Children cp = new Parent();
        System.out.println("**********");*/

    }

}
