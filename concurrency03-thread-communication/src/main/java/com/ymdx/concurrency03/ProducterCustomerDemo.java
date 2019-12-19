package com.ymdx.concurrency03;

/**
 * @ClassName: ProducterCustomerDemo
 * @Description: 生产者与消费者
 * 示例（两个线程协同工作，先生产，再消费）：第一个线程写入(input)用户，另一个线程取读取(out)用户。实现读一个，写一个操作。
 * <p>
 * 面试题：sleep与wait的区别
 * sleep：让线程进入休眠状态，让出CPU的时间片，不释放对象监视器的所有权（对象锁）
 * wait：让线程进入等待状态，让出CPU的时间片，并释放对象监视器的所有权（对象锁），等待其它线程通过notify或notifyAll方法来唤醒
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-19 11:42
 * @Version: 1.0
 **/
public class ProducterCustomerDemo {

    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        Producter producter = new Producter(user);
        Customer customer = new Customer(user);
        Thread t1 = new Thread(producter, "t1");
        Thread t2 = new Thread(customer, "t2");
        t1.start();
        t2.start();
    }

}

/**
 * 用户，线程共享资源
 */
class User {
    private String name;
    private String sex;
    /**
     * true：可以读，但不可以写
     * false：可以写，但不可以读
     */
    public boolean flag = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

/**
 * 生产者，写操作线程
 */
class Producter implements Runnable {
    private User user;

    public Producter(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (user) {
                if (user.flag) {
                    // flag = true，可以读，但不可以写
                    try {
                        // 释放当前监视器锁，让出CPU执行权
                        user.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (count == 0) {
                    user.setName("小明");
                    user.setSex("男");
                } else {
                    user.setName("小丽");
                    user.setSex("女");
                }
                user.flag = true;
                // 唤醒等待的线程（随机的其中一个）
                user.notify();
            }
            count = (count + 1) % 2;
        }
    }

}

/**
 * 消费者，读操作线程
 */
class Customer implements Runnable {
    private User user;

    public Customer(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (user) {
                if(!user.flag){
                    // flag = false：可以写，但是不可以读
                    try {
                        user.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(user);
                user.flag = false;
                user.notify();
            }
        }
    }

}


