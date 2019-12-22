package com.ymdx.concurrency04.future;

/**
 * @ClassName: Main
 * @Description: 调用者
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-22 22:15
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        FutureClient futureClient = new FutureClient();
        Data request = futureClient.request("req args");
        System.out.println("请求发送成功!");
        System.out.println("执行其他任务...");
        String result = request.getData();
        System.out.println("结果为：" + result);
    }

}
