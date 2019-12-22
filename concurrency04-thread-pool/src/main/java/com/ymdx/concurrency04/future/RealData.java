package com.ymdx.concurrency04.future;

/**
 * @ClassName: RealData
 * @Description: 真实数据RealData
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-22 22:01
 * @Version: 1.0
 **/
public class RealData implements Data {
    private String result;

    public RealData(String reqStr) {
        System.out.println("正在使用reqStr=" + reqStr + "请求数据，耗时操作需要等待...");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("操作完毕，获取结果...");
        result = "OK";
    }

    @Override
    public String getData() {
        return result;
    }
}
