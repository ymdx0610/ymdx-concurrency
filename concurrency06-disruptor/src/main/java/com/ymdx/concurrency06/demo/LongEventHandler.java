package com.ymdx.concurrency06.demo;

import com.lmax.disruptor.EventHandler;

/**
 * @ClassName: LongEventHandler
 * @Description: 事件消费者，也就是一个事件处理器。
 * 这个事件处理器简单地把事件中存储的数据打印到终端。
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-25 09:26
 * @Version: 1.0
 **/
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者接收到数据：" + event.getValue());
    }
}
