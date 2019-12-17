package com.ymdx.concurrency01;

/**
 * @ClassName: ThreadJoinDemo
 * @Description: join()方法示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-17 16:28
 * @Version: 1.0
 **/
public class ThreadJoinDemo {

    public static void main(String[] args) {
        System.out.println("main-thread start...");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("hello, sub-thread, i=" + 1);
                }
            }
        });
        thread.start();
        try {
            // thread.join() 主线程让出CPU执行权给thread，等待thread执行完，再继续执行主线程
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("hello, main-thread, i=" + i);
        }
        System.out.println("main-thread end.");
    }

}
