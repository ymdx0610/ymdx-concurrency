package com.ymdx.concurrency02;

/**
 * @ClassName: Test01
 * @Description: 同步方法使用this锁证明。
 *
 * 证明方式: 一个线程使用同步代码块(this锁)，另一个线程使用同步方法。如果两个线程抢票不能实现同步，那么会出现数据错误。
 *
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-18 11:02
 * @Version: 1.0
 **/
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        TrainTicketThread trainTicketThread = new TrainTicketThread();
        Thread t1 = new Thread(trainTicketThread, "窗口1");
        Thread t2 = new Thread(trainTicketThread, "窗口2");
        t1.start();
        Thread.sleep(200);
        trainTicketThread.flag = false;
        t2.start();
    }
}

class TrainTicketThread implements Runnable {
    private int ticketCount = 100;
    public boolean flag = true;
    private Object obj = new Object();

    @Override
    public void run() {
        if (flag) {
            while (ticketCount > 0) {
//                try {
//                    Thread.sleep(50);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                synchronized (this) {
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (ticketCount > 0) {
                        System.out.println(Thread.currentThread().getName() + "," + "正在出售第" + (100 - ticketCount + 1) + "张票...");
                        ticketCount--;
                    }
                }
            }
        } else {
            while (ticketCount > 0) {
//                try {
//                    Thread.sleep(50);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                sale();
            }
        }
    }

    public synchronized void sale() {
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ticketCount > 0) {
            System.out.println(Thread.currentThread().getName() + "," + "正在出售第" + (100 - ticketCount + 1) + "张票...");
            ticketCount--;
        }
    }
}

