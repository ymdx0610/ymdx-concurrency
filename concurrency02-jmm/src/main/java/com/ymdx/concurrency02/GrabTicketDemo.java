package com.ymdx.concurrency02;

/**
 * @ClassName: GrabTicketDemo
 * @Description: 线程安全示例：多窗口抢票
 *
 * 案例：现在有100张火车票，有两个窗口同时抢火车票，请使用多线程模拟抢票效果。
 *
 * 多个线程共享同一个全局成员变量时，做写的操作可能会发生数据冲突问题（线程安全问题）
 *
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-18 09:48
 * @Version: 1.0
 **/
public class GrabTicketDemo {

    public static void main(String[] args) {
        TicketSalesThread ticketSalesThread = new TicketSalesThread();
        Thread t1 = new Thread(ticketSalesThread, "窗口1");
        Thread t2 = new Thread(ticketSalesThread, "窗口2");
        t1.start();
        t2.start();
    }

}

class TicketSalesThread implements Runnable{
    /** 多个窗口共享100张票 */
//    private int count = 100;
    private static int count = 100;

    @Override
    public void run() {
        while (count > 0){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sale();
        }
    }

    // 静态同步方法
    private static void sale() {
        synchronized (TicketSalesThread.class){
            if(count > 0){
                System.out.println(Thread.currentThread().getName() + ", 正在出售第" + (100 - count + 1) + "张票...");
                count --;
            }
        }
    }

//    private void sale() {
//        // 同步代码块，锁可以是this或者任意全局对象
//        synchronized (this){
//            if(count > 0){
//                System.out.println(Thread.currentThread().getName() + ", 正在出售第" + (100 - count + 1) + "张票...");
//                count --;
//            }
//        }
//    }

    // 同步非静态方法，使用的是this锁
//    private synchronized void sale() {
//        // 注意这里必须再判断一次。当余票只剩最后一张，假设窗口1先获取到锁，执行下面的购买，将买到第100张票，此时余票为0。
//        // 与此同时，窗口1释放锁，并由窗口2获取到锁继续购票，则将买到第101张票。即发生了超票购买的线程安全问题。。。
//        if(count > 0){
//            System.out.println(Thread.currentThread().getName() + ", 正在出售第" + (100 - count + 1) + "张票...");
//            count --;
//        }
//    }

}
