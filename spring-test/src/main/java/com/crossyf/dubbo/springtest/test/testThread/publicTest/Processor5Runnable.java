package com.crossyf.dubbo.springtest.test.testThread.publicTest;

public class Processor5Runnable implements Runnable{

    public Boolean run = true;//利用Boolean来控制

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            if(run){
                try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"-->"+i);
            }else{
                return; //结束此方法
            }
        }
    }
}
