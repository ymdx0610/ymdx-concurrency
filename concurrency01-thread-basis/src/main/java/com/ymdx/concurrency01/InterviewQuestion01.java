package com.ymdx.concurrency01;

/**
 * @ClassName: InterviewQuestion01
 * @Description: 面试题示例
 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？ 
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-17 16:42
 * @Version: 1.0
 **/
public class InterviewQuestion01 {

    public static void main(String[] args) {
        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "----" +i);
                }
            }
        });

        Thread T2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    T1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "----" +i);
                }
            }
        });

        Thread T3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    T2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "----" +i);
                }
            }
        });

        T1.setName("T1");
        T2.setName("T2");
        T3.setName("T3");
        T1.start();
        T2.start();
        T3.start();
    }

}
