package com.ymdx.concurrency04;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @ClassName: SemaphoreDemo
 * @Description: Semaphore示例
 *
 * 需求: 一个厕所只有3个坑位，但是有10个人来上厕所，那怎么办？
 * 假设10个人的编号分别为1-10，并且1号先到厕所，10号最后到厕所。
 * 那么1-3号来的时候必然有可用坑位，顺利如厕，4号来的时候需要看看前面3人是否有人出来了，如果有人出来，进去，否则等待。
 * 同样的道理，4-10号也需要等待正在上厕所的人出来后才能进去，并且谁先进去这得看等待的人是否有素质，是否能遵守先来先上的规则。
 *
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-19 18:25
 * @Version: 1.0
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 10; i++) {
            WcThread wcThread = new WcThread("第" + i + "个人", semaphore);
            wcThread.start();
        }
    }

}

class WcThread extends Thread {
    private String name;
    private Semaphore wc;

    public WcThread(String name, Semaphore wc) {
        this.name = name;
        this.wc = wc;
    }

    @Override
    public void run() {
        // 剩下的资源
        int availablePermits = wc.availablePermits();
        if (availablePermits > 0) {
            System.out.println(name + "天助我也，终于有茅坑了.....");
        } else {
            System.out.println(name + "怎么没有茅坑了...");
        }
        try {
            // 申请资源
            wc.acquire();
            System.out.println(name + "终于上厕所啦，爽啊" + "，剩下厕所:" + wc.availablePermits());
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(name + "厕所上完啦！");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 释放资源
            wc.release();
        }
    }
}

