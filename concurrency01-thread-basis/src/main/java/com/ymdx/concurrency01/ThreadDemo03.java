package com.ymdx.concurrency01;

/**
 * @ClassName: ThreadDemo03
 * @Description: 创建线程：匿名内部类，实现run方法
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-17 14:22
 * @Version: 1.0
 **/
public class ThreadDemo03 {

    public static void main(String[] args) {
        System.out.println("------主线程开始------");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("子线程打印：hello, 义码当仙-" + i);
                }
            }
        });
        System.out.println("------启动子线程------");
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程调用-" + i);
        }
        System.out.println("------主线程结束------");
    }

}
