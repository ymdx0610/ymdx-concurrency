package com.ymdx.concurrency04;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName: ConcurrentLinkedQueueDemo
 * @Description: ConcurrentLinkedQueueDemo示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-20 14:32
 * @Version: 1.0
 **/
public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) {
        // 非阻塞队列，无界
        ConcurrentLinkedQueue<String> cld = new ConcurrentLinkedQueue<>();
        // 向队列中添加元素 add/offer
        cld.offer("刘备");
        cld.offer("关羽");
        cld.offer("张飞");
        cld.add("义码当仙");
        // 获取一个队列元素，并从队列中删除这个元素
        System.out.println(cld.poll());
        // 获取队首元素，但不从队列删除此元素
        System.out.println(cld.peek());
        System.out.println(cld.poll());
        System.out.println(cld.poll());
        System.out.println(cld.poll());
        // 获取队列元素个数
        System.out.println("当前队列元素个数：" + cld.size());
    }

}
