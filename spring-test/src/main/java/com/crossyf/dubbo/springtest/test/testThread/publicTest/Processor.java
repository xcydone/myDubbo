package com.crossyf.dubbo.springtest.test.testThread.publicTest;

public class Processor extends Thread{

    public int i = 1;

    @Override
    public void run() {
        System.out.println("我是进程i:" + i);
        i++;

    }
}
