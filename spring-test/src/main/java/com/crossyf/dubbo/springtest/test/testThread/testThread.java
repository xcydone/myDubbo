package com.crossyf.dubbo.springtest.test.testThread;

public class testThread {

    public static void main(String[] args){

        Processor1 p1 = new Processor1();
        Thread t1 = new Thread(p1);

        Processor2 p2 = new Processor2();
        Thread t2 = new Thread(p2);

        t1.setName("线程01");
        t1.setPriority(10);

        t2.setName("线程02");
        t2.setPriority(5);

        t1.start();
        t2.start();
    }

}
