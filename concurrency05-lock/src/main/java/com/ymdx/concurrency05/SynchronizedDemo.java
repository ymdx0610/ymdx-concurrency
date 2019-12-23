package com.ymdx.concurrency05;

/**
 * @ClassName: SynchronizedDemo
 * @Description: 可重入锁（synchronized）示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-23 09:56
 * @Version: 1.0
 **/
public class SynchronizedDemo implements Runnable{
    @Override
    public void run() {
        set();
    }

    private synchronized void set(){
        System.out.println(Thread.currentThread().getName() + " --- This is set method.");
        get();
    }

    private synchronized void get(){
        // 若此方法能够正常被执行打印，说明synchronized具有可重入性
        System.out.println(Thread.currentThread().getName() + " --- This is get method.");
    }

    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        new Thread(synchronizedDemo).start();
        new Thread(synchronizedDemo).start();
        new Thread(synchronizedDemo).start();
    }

}
