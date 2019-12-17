package com.ymdx.concurrency01;

/**
 * @ClassName: DaemonThreadDemo
 * @Description: 守护线程示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-17 15:37
 * @Version: 1.0
 **/
public class DaemonThreadDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("hello, deamon-thread, i=" + 1);
                }
            }
        });
        // setDaemon(true) 将thread设置为守护线程，将随着主线程的销毁而销毁。
        // 注，setDaemon(true)必须在start()方法前设置，否则不生效
        thread.setDaemon(true);
        thread.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("hello, main-thread, i=" + i);
        }
        System.out.println("main-thread execute over...");
    }

}
