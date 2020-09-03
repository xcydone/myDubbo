package com.crossyf.dubbo.springtest.test.testThread.publicTest;

public class Processor4Runnable implements Runnable{
    @Override
    public void run() {
        System.out.println("running!");

        try{
            Thread.sleep(10000000);
        }catch (InterruptedException e){

        }

        for(int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName()+"-->"+i);
        }
    }
}
