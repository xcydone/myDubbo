package com.crossyf.dubbo.springtest.test.testThread;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        final ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory());

        // 最大容量应该是3+5=8
        for (int i = 0; i < 9; i++) {
            pool.execute(new Task(i));
        }

        pool.shutdown();
    }

    static class Task implements Runnable{

        private int nov;

        public Task(int nov) {
            this.nov = nov;
        }

        @Override
        public void run() {
            System.out.println("执行当前任务的线程是：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("我是任务：" + nov +"我在执行。。。");
        }
    }
}
