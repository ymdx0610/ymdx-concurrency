package com.ymdx.concurrency05;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: ReadWriteLockDemo
 * @Description: 读写锁
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-23 11:15
 * @Version: 1.0
 **/
public class ReadWriteLockDemo {

    private Map<String, Object> cache = new HashMap<>();

    private ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
    private Lock readLock = rrwl.readLock();
    private Lock writeLock = rrwl.writeLock();

    private void put(String key, Object value) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "，正在写入缓存...（key=" + key + ", value=" + value + "）");
            Thread.sleep(100);
            cache.put(key, value);
            System.out.println(Thread.currentThread().getName() + "，写入缓存（key=" + key + ", value=" + value + "）结束。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    private void get(String key) {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "，正在读取缓存...（key=" + key + "）");
            Thread.sleep(100);
            cache.get(key);
            System.out.println(Thread.currentThread().getName() + "，读取缓存（key=" + key + "）结束。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    readWriteLockDemo.put(i + "", i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    readWriteLockDemo.get(i + "");
                }
            }
        }).start();
    }

}
