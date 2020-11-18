package com.crossyf.dubbo.springtest.test.testThread;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定线程池--容量池设置，无界队列，响应时间为0
 *
 * 5.keepAliveTime
 * 　　主要看是控制的是谁。
 * 　　如果线程池的当前的线程数多于了corePoolSize，那么多于的线程空闲时间超过keepAliveTime，将会被终止
 * 　　减少资源消耗

 * 6.ThreadFactory
 * 　　新的线程默认使用Exectors.defaultThreadFactory()，创建的线程都在一个线程组，拥有相同的NORM_PRIORITY优先级，并且都不是守护线程

 * 7.workQueue
 * 　　工作队列
 * 　　最常见的队列类型：
 * 　　　　直接交换：SynchronousQueue，内部没有容量
 * 　　　　无界队列：LinkedBlockingQueue
 * 　　　　有界队列：ArrayBlockingQueue
 */
public class FixedThreadPoolTest {

    public static void main(String[] args) {
        // 创建线程池，让线程池去执行任务，区别于每个线程去执行任务
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        System.out.println(Integer.MAX_VALUE); // 2147483647
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new Task());
        }

    }

    static class Task implements Runnable{
        @Override
        public void run() {
            try{
                Thread.sleep(500000); // 50s
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("任务执行了");
        }
    }


}
