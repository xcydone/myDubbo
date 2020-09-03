package com.crossyf.dubbo.springtest.test.testThread.publicTest;

import java.util.ArrayList;
import java.util.List;

public class Processor1 extends Thread{

    public int i = 1;

    @Override
    public void run() {

        while(true){
            if(i > 5){
                break;
            }
            System.out.println("进程1执行了");
            i++;
        }

        /*Thread currThread = Thread.currentThread ();
        synchronized (currThread){
            while(true){
                if(i > 5){
                    break;
                }
                System.out.println("进程1执行了");
                i++;
                currThread.notify();
            }
        }*/
    }
}
