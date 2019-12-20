package com.ymdx.concurrency04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ArrayBlockingQueueDemo
 * @Description: ArrayBlockingQueue示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-20 15:24
 * @Version: 1.0
 **/
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        // 入队操作，若队列已满，则会等待；出队操作，若队列为空，则会等待。
        BlockingQueue bq = new ArrayBlockingQueue(3);
        bq.add("刘备");
        bq.add("关羽");
        bq.add("张飞");

        // Exception in thread "main" java.lang.IllegalStateException: Queue full
//        bq.add("义码当仙");

        System.out.println(bq.poll());
        bq.offer("义码当仙", 3, TimeUnit.SECONDS);
        System.out.println(bq.size());
        System.out.println(bq.poll());
        System.out.println(bq.size());

        System.out.println(bq.poll());
        System.out.println(bq.size());
        System.out.println(bq.poll());
        System.out.println(bq.size());

        // 此时队列为null
        System.out.println(bq.peek());
        System.out.println("阻塞获取队列元素：" + bq.poll(5, TimeUnit.SECONDS));

    }

}
