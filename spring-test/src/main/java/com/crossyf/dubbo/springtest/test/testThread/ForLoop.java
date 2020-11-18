package com.crossyf.dubbo.springtest.test.testThread;

public class ForLoop {
    public static void main(String[] args) {

        // 反复的创建线程不能重复使用，开销大，反复进行创建和销毁
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
    }


    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println("任务执行了");
        }
    }
}
