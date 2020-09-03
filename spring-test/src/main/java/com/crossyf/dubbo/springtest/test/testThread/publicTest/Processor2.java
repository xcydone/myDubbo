package com.crossyf.dubbo.springtest.test.testThread.publicTest;

public class Processor2 extends Thread{
    public int i = 1;

    @Override
    public void run() {

        while(true){
            if(i > 5){
                break;
            }
            System.out.println("进程2执行了");
            i++;
        }

        /*Thread currThread = Thread.currentThread ();
        synchronized (currThread){
            while(true){
                if(i > 4){
                    break;
                }
                System.out.println("进程2执行了");
                i++;
                currThread.notify();
            }
        }*/
        /*Thread currThread = Thread.currentThread ();
        synchronized (currThread){
            while ("t1".equals (currThread.getName ())) {
                System.out.println ("当前线程是t1");

                try {
                    currThread.wait (0);

                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
            done();
        }*/
    }

    /*public synchronized void done () {
        System.out.println ("更改完毕");
        System.out.println ("当前线程是t2");
        while(true){
            if(i > 5){
                break;
            }
            System.out.println("进程2执行了");
            i++;
        }
    }*/
}
