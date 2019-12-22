package com.ymdx.concurrency04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadPoolDemo
 * @Description: 线程池的四种创建方式示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-22 11:34
 * @Version: 1.0
 **/
public class ThreadPoolDemo {

    public static void main(String[] args) {
//        createNewCachedThreadPool();
//        createNewFixedThreadPool();
//        createNewScheduledThreadPool();
        createNewSingleThreadExecutor();
    }

    public static void createNewCachedThreadPool(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int t = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "---" + t);
                }
            });
        }
        executorService.shutdown();
    }

    public static void createNewFixedThreadPool(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int t = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "---" + t);
                }
            });
        }
        executorService.shutdown();
    }

    public static void createNewScheduledThreadPool(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 5; i++) {
            final int t = i;
            scheduledExecutorService.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "---" + t);
                }
            }, 5, TimeUnit.SECONDS);
        }
        scheduledExecutorService.shutdown();
    }

    public static void createNewSingleThreadExecutor(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int t = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "---" + t);
                }
            });
        }
        executorService.shutdown();
    }
}
