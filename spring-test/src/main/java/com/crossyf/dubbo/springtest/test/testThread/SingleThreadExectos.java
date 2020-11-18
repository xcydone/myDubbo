package com.crossyf.dubbo.springtest.test.testThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单一线程池：容量为1
 */
public class SingleThreadExectos {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new Task());
        }
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            try{
                Thread.sleep(5000); // 5s
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("任务执行了");
        }
    }
}
