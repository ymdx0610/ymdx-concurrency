package com.ymdx.concurrency04.future;

/**
 * @ClassName: FutureData
 * @Description: FutureData, 当有线程想要获取RealData的时候，程序会被阻塞。等到RealData被注入才会使用getReal()方法。
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-22 22:05
 * @Version: 1.0
 **/
public class FutureData implements Data {
    public volatile static boolean FLAG = false;
    private RealData realData;

    public synchronized void setRealData(RealData realData) {
        if (FLAG) {
            return;
        }
        // 如果没有获取到数据，传递真实数据
        this.realData = realData;
        FLAG = true;
        notify();
    }

    @Override
    public synchronized String getData() {
        while (!FLAG) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 获取到数据，直接返回
        return realData.getData();
    }

}
