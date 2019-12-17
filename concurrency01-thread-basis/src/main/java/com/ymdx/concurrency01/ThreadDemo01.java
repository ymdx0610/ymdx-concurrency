package com.ymdx.concurrency01;

/**
 * @ClassName: ThreadDemo01
 * @Description: 创建线程：继承Thread方式，重写run方法
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-17 11:17
 * @Version: 1.0
 **/
public class ThreadDemo01 {

    public static void main(String[] args) {
        System.out.println("------主线程开始------");
        // 1.创建线程
        MyThread myThread = new MyThread();
        System.out.println("------启动子线程------");
        // 2.通过start方法启动线程，而不是调用run方法
//        myThread.run();
        myThread.start();
        for(int i=0; i < 10; i++){
            System.out.println("主线程调用-" + i);
        }
        System.out.println("------主线程结束------");
    }

}

class MyThread extends Thread{
    @Override
    public void run() {
        // 编写多线程需要执行的代码
        for (int i = 0; i < 10; i++) {
            System.out.println("子线程打印：hello, 义码当仙-" + i);
        }
    }
}