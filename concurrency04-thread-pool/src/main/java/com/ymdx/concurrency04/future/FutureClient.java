package com.ymdx.concurrency04.future;

/**
 * @ClassName: FutureClient
 * @Description: 客户端
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-22 22:14
 * @Version: 1.0
 **/
public class FutureClient {

    public Data request(String reqStr) {
        FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(reqStr);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }

}
