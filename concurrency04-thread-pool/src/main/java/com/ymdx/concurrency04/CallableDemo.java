package com.ymdx.concurrency04;

import java.util.concurrent.*;

/**
 * @ClassName: CallableDemo
 * @Description: Callable示例
 *
 * 异步方式获取线程执行结果
 *
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-22 20:05
 * @Version: 1.0
 **/
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(Thread.currentThread().getName() + "，正在执行...");
        Future<String> submit = executorService.submit(new MyCallable());
        String result = submit.get();
        // System.out.println(Thread.currentThread().getName() + "，执行完毕～～～\nresult = " + result);

        // 重新开启新的子线程获取结果，防止主线程被阻塞
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "，执行完毕～～～\nresult = " + result);
            }
        }).start();
        System.out.println(Thread.currentThread().getName() + "，执行完毕～～～");

        executorService.shutdown();
    }

}

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "，正在执行...");
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "，执行完毕～～～");
        return "hello, ymdx!";
    }

}