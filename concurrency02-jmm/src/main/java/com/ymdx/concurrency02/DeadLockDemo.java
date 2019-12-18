package com.ymdx.concurrency02;

/**
 * @ClassName: DeadLockDemo
 * @Description: 死锁示例
 *
 * t1先获取到obj锁，再获取this锁
 * t2先获取到this锁，再获取obj锁
 *
 * 当t1获取this锁时，需要等待t2释放this锁；当t2获取obj锁时，需要等待t1释放obj锁；
 * t1、t2无限期的相互等待，造成死锁，死锁期间在不断消耗系统资源。
 *
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-18 13:31
 * @Version: 1.0
 **/
public class DeadLockDemo {
    public static void main(String[] args) throws InterruptedException {
        TestThread testThread = new TestThread();
        Thread t1 = new Thread(testThread, "窗口1");
        Thread t2 = new Thread(testThread, "窗口2");
        t1.start();
        Thread.sleep(40);
        testThread.flag = false;
        t2.start();
    }
}

class TestThread implements Runnable {
    private int trainCount = 100;
    private Object obj = new Object();
    public boolean flag = true;

    @Override
    public void run() {
        if (flag) {
            while (trainCount > 0) {
                synchronized (obj) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sale();
                }
            }
        } else {
            while (trainCount > 0) {
                sale();
            }
        }
    }

    public synchronized void sale() {
        synchronized (obj) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (trainCount > 0) {
                System.out.println(Thread.currentThread().getName() + "," + "正在出售第" + (100 - trainCount + 1) + "张票...");
                trainCount--;
            }
        }
    }

}


