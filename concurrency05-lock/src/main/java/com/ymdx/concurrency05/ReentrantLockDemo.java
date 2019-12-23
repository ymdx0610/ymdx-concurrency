package com.ymdx.concurrency05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockDemo
 * @Description: 可重入锁（ReentrantLock）示例
 *
 * 可重入锁，可以递归传递，目的是避免死锁
 *
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-23 10:04
 * @Version: 1.0
 **/
public class ReentrantLockDemo implements Runnable {

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        set();
    }

    private void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " --- This is set method.");
            get();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private void get(){
        lock.lock();
        try{
            // 若此方法能够正常被执行打印，说明ReentrantLock具有可重入性
            System.out.println(Thread.currentThread().getName() + " --- This is get method.");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        new Thread(reentrantLockDemo).start();
        new Thread(reentrantLockDemo).start();
        new Thread(reentrantLockDemo).start();
    }

}
