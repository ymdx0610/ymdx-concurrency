package com.ymdx.concurrency02;

/**
 * @ClassName: ThreadLocalDemo
 * @Description: ThreadLocal示例
 * 创建三个线程，每个线程生成自己独立的序列号。
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-18 15:00
 * @Version: 1.0
 **/
public class ThreadLocalDemo {

    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1(new Seq());

        Thread t1 = new Thread(testThread1, "thread-t1");
        Thread t2 = new Thread(testThread1, "thread-t2");
        Thread t3 = new Thread(testThread1, "thread-t3");

        t1.start();
        t2.start();
        t3.start();
    }

}

class Seq {
    private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public Integer getSeqNumber() {
        Integer count = threadLocal.get() + 1;
        threadLocal.set(count);
        return count;
    }
}

class TestThread1 implements Runnable{

    private Seq seq;

    public TestThread1(Seq seq){
        this.seq = seq;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + seq.getSeqNumber());
        }
    }
}

