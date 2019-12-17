package com.ymdx.concurrency01;

/**
 * @ClassName: ThreadPriorityDemo
 * @Description: 线程优先级示例
 * 优先级范围为1-10，其中10最高，默认值为5
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-17 16:35
 * @Version: 1.0
 **/
public class ThreadPriorityDemo {

    public static void main(String[] args) {
        PriorityThread priorityThread = new PriorityThread();
        Thread t1 = new Thread(priorityThread);
        Thread t2 = new Thread(priorityThread);
        t1.start();
        // 注意：设置了优先级，不代表每次都一定会被执行。只是CPU调度会有限分配
        t1.setPriority(10);
        t2.start();
    }

}

class PriorityThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().toString() + "---i:" + i);
        }
    }
}