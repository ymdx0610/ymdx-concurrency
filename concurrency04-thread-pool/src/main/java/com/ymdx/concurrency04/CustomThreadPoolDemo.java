package com.ymdx.concurrency04;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CustomThreadPoolDemo
 * @Description: 自定义线程池示例
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-22 13:13
 * @Version: 1.0
 **/
public class CustomThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(3));

        // 任务1，新建线程1，执行
        threadPoolExecutor.execute(new MyTask("任务1"));
        // 任务2、3、4缓存到队列
        threadPoolExecutor.execute(new MyTask("任务2"));
        threadPoolExecutor.execute(new MyTask("任务3"));
        threadPoolExecutor.execute(new MyTask("任务4"));
        // 任务5，创建线程2，执行
        threadPoolExecutor.execute(new MyTask("任务5"));
        // 任务6，队列已满，并且已经达到对最大线程数，拒绝策略处理，这里将抛出异常
//        threadPoolExecutor.execute(new MyTask("任务6"));
        threadPoolExecutor.shutdown();
    }
}

class MyTask implements Runnable{
    private String taskName;
    public MyTask(String taskName){
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "，正在执行..." + taskName);
    }
}
