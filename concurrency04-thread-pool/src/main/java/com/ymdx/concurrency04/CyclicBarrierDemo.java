package com.ymdx.concurrency04;

import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName: CyclicBarrierDemo
 * @Description: CyclicBarrier示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-19 18:12
 * @Version: 1.0
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            Writer writer = new Writer(cyclicBarrier);
            writer.start();
        }
    }
}

class Writer extends Thread {
    private CyclicBarrier cyclicBarrier;

    public Writer(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("线程" + Thread.currentThread().getName() + "，正在写入数据...");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "，写入数据成功。");
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("所有线程执行完毕～");
    }

}
