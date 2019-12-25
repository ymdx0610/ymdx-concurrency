package com.ymdx.concurrency06.demo;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @ClassName: LongEventProducer
 * @Description: 生产者，发送事件
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-25 09:28
 * @Version: 1.0
 **/
public class LongEventProducer {
    public final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        // 1.ringBuffer 事件队列 下一个槽
        long sequence = ringBuffer.next();
        Long data = null;
        try {
            // 2.取出空的事件队列
            LongEvent longEvent = ringBuffer.get(sequence);
            data = byteBuffer.getLong(0);
            // 3.获取事件队列传递的数据
            longEvent.setValue(data);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println("生产者正在发送数据...");
            // 4.发布事件
            ringBuffer.publish(sequence);
        }
    }

}
