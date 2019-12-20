package com.ymdx.concurrency04;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: LinkedBlockingQueueDemo
 * @Description: LinkedBlockingQueue示例
 * 生产者与消费者
 *
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-20 16:23
 * @Version: 1.0
 **/
public class LinkedBlockingQueueDemo {

    public static void main(String[] args) {
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        ProducerThread producerThread = new ProducerThread(blockingQueue);
        ConsumerThread consumerThread = new ConsumerThread(blockingQueue);
        Thread t1 = new Thread(producerThread, "producerThread");
        Thread t2 = new Thread(consumerThread, "consumerThread");
        t1.start();
        t2.start();
        try {
            Thread.sleep(1000 * 10);
            producerThread.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ProducerThread implements Runnable{

    private BlockingQueue<String> blockingQueue;

    public ProducerThread(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    private AtomicInteger atomicInteger = new AtomicInteger();
    private volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "，正在运行...");
        try {
            while(flag){
                String data = "数据-" + atomicInteger.incrementAndGet();
                boolean offer = blockingQueue.offer(data, 3, TimeUnit.SECONDS);
                if(offer){
                    System.out.println("生产者生产数据（"+data+"）成功！");
                }else {
                    System.out.println("生产者生产数据（"+data+"）失败！");
                }
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName() + "，运行结束.");
        }

    }

    public void stop(){
        this.flag = false;
    }
}

class ConsumerThread implements Runnable{

    private BlockingQueue<String> blockingQueue;
    private volatile boolean flag = true;


    public ConsumerThread(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "，正在运行...");
        try {
            while (flag) {
                String data = blockingQueue.poll(3, TimeUnit.SECONDS);
                if(StringUtils.isBlank(data)){
                    flag = false;
                    System.out.println("消费者超时3秒，未获取到任何数据～～～");
                    return ;
                }
                System.out.println("消费者获取到" + data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "，运行结束.");
        }
    }
}
