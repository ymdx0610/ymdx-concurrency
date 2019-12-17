package com.ymdx.concurrency01;

/**
 * @ClassName: ThreadDemo02
 * @Description: 创建线程：实现Runnable接口，实现run方法
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-17 13:58
 * @Version: 1.0
 **/
public class ThreadDemo02{
    public static void main(String[] args) {
        System.out.println("主线程开始...");
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        System.out.println("------启动子线程------");
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程调用-" + i);
        }
        System.out.println("主线程结束.");
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        // 编写多线程需要执行的代码
        for (int i = 0; i < 10; i++) {
            System.out.println("子线程打印：hello, 义码当仙-" + i);
        }
    }
}
