package com.crossyf.dubbo.springtest.test.testThread;

public class Processor1 extends Thread{

    public int i = 1;

    @Override
    public void run() {

        Thread currThread = Thread.currentThread ();
        synchronized (currThread){
            while(true){
                if(i > 5){
                    break;
                }
                System.out.println("进程1执行了");
                i++;
                currThread.notify();
            }
        }
    }
}
