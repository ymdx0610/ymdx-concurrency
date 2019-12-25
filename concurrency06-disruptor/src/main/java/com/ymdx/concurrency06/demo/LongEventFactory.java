package com.ymdx.concurrency06.demo;

import com.lmax.disruptor.EventFactory;

/**
 * @ClassName: LongEventFactory
 * @Description: 实例化LongEvent的工厂
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-25 09:24
 * @Version: 1.0
 **/
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
