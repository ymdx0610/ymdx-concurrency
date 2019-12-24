package com.ymdx.concurrency05;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: AtomicIntegerDemo
 * @Description: AtomicInteger示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-24 09:44
 * @Version: 1.0
 **/
public class AtomicIntegerDemo {

    public static void main(String[] args) {
//        System.out.println("------synchronized------");
//        MyThread1 myThread1 = new MyThread1();
//        Thread t1 = new Thread(myThread1);
//        Thread t2 = new Thread(myThread1);
//        t1.start();
//        t2.start();

        System.out.println("------AtomicInteger------");
        MyThread2 myThread2 = new MyThread2();
        Thread t3 = new Thread(myThread2);
        Thread t4 = new Thread(myThread2);
        t3.start();
        t4.start();
    }

}

/**synchronized：具有可重入性，保证原子性和可见性。但为重量级锁，加锁时阻塞其它线程的访问*/
class MyThread1 implements Runnable {
    private int count = 1;

    @Override
    public void run() {
        while (true) {
            Integer count = getCount();
            if (count > 100) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "---" + count);
        }
    }

    private synchronized Integer getCount() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count++;
    }
}

/**AtomicInteger：底层使用CAS无锁机制实现，相当于乐观锁*/
class MyThread2 implements Runnable{
    private AtomicInteger count = new AtomicInteger();

    @Override
    public void run() {
        while (true) {
            Integer count = getCount();
            if (count > 100) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "---" + count);
        }
    }

    private Integer getCount() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count.incrementAndGet();
    }

}