package com.crossyf.dubbo.springtest.test.testThread;

public class jvmTest {

    // 定义共享变量，保证多线程访问变量可见性，没有这个关键词，线程1不能跳出while
    private static volatile Boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {

        // 等待执行的第一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我在等数据");
                while(!initFlag){

                }
                System.out.println("执行完成");
            }
        }).start();

        Thread.sleep(2000);

        // 准备数据的一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                preparedData();
            }
        }).start();
    }

    public static void preparedData(){
        System.out.println("我正在准备数据。");

        initFlag = true;

        System.out.println("数据准备完成。");
    }

}
