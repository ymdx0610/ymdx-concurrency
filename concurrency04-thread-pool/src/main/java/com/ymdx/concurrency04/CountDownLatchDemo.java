package com.ymdx.concurrency04;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: CountDownLatchDemo
 * @Description: CountDownLatch示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-19 17:54
 * @Version: 1.0
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "，子线程开始执行...");
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "，子线程结束执行...");
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "，子线程开始执行...");
                // 计数器值每次减去1
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "，子线程结束执行...");
            }
        }).start();

        // 当计数器的值为0，恢复任务继续执行
        countDownLatch.await();

        System.out.println("两个子线程执行完毕....");

        System.out.println("主线程继续执行.....");
        for (int i = 0; i < 10; i++) {
            System.out.println("main,i:" + i);
        }

    }
}
