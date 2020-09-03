package com.crossyf.dubbo.springtest.test.testThread;

import com.crossyf.dubbo.springtest.test.testThread.lock.AddMoneyThread;
import com.crossyf.dubbo.springtest.test.testThread.lock.Bank;
import com.crossyf.dubbo.springtest.test.testThread.lock.Bank2;
import com.crossyf.dubbo.springtest.test.testThread.lock.SubstractMoneyThread;
import com.crossyf.dubbo.springtest.test.testThread.publicTest.Processor1;
import com.crossyf.dubbo.springtest.test.testThread.publicTest.Processor2;
import com.crossyf.dubbo.springtest.test.testThread.publicTest.Processor4Runnable;
import com.crossyf.dubbo.springtest.test.testThread.publicTest.Processor5Runnable;

public class testThread {

    public static void main(String[] args) throws InterruptedException{

        /*testSingleThread();*/

        /*testRunnableThreadStop1();*/

        /*testRunnableThreadStop2();*/

        testSynchronizedObject();
    }

    public static void testRunnableThreadStop1() throws InterruptedException{
        Thread t4 = new Thread(new Processor4Runnable());  // 实现的runnable接口
        t4.setName("进程4");
        t4.start();
        Thread.sleep(5000);
        t4.interrupt(); //打断 t4 的长时间休眠
    }

    public static void testRunnableThreadStop2() throws InterruptedException{
        Processor5Runnable p =new Processor5Runnable();
        Thread t=new Thread(p);
        t.setName("t5");
        t.start();
        Thread.sleep(5000);
        p.run=false; //终止   只执行了5次
    }

    public static void testSingleThread(){
        // 异步模型
        Thread t1 = new Processor1(); //创建线程 继承的Thread类
        t1.setName("t1");//给线程起名
        t1.setPriority(5);//设置优先级(由低到高1~10)

        Thread t2 = new Processor2();
        t2.setName("t2");
        t2.setPriority(10);

        t1.start();//告诉JVM再分配一个新的栈给t线程，run不需程序员手动调用
        t2.start();//系统线程启动后自动调用run方法
    }

    /*
    * 什么条件下要使用线程同步？
    *
    * 必须是多线程环境
    * 多线程环境共享同一个数据
    * 共享的数据涉及到修改操作
    */
    public static void testMutilThread(){
        // 同步编程模型

        /*Processor1 p1 = new Processor1();
        Thread t1 = new Thread(p1);

        Processor2 p2 = new Processor2();
        Thread t2 = new Thread(p2);

        t1.setName("线程01");
        t1.setPriority(10);

        t2.setName("线程02");
        t2.setPriority(5);

        t1.start();
        t2.start();*/
    }

    public static void testSynchronizedObject() {
        // 锁共享数据，实现线程同步执行，线程1执行完成之后执行线程2
        /*Bank bank = new Bank(1000);*/
        Bank2 bank = new Bank2(1000);

        SubstractMoneyThread subThread = new SubstractMoneyThread(bank, 10);
        AddMoneyThread addThread = new AddMoneyThread(bank, 10);

        Thread subT = new Thread(subThread);
        Thread addT = new Thread(addThread);

        subT.start();  // 谁先启动谁先获得线权开始执行，释放资源之后，才可以到下一个线程
        addT.start();
    }

}
