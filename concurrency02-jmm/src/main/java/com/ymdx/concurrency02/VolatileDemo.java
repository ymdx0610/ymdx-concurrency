package com.ymdx.concurrency02;

/**
 * @ClassName: VolatileDemo
 * @Description: volatile示例
 *
 * volatile：保证可见性，也就是说一旦某个线程修改了该被volatile修饰的变量，它会保证修改的值会立即被更新到主存，当有其他线程需要读取时，可以立即获取修改之后的值。
 *
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-12-19 09:41
 * @Version: 1.0
 **/
public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        TestVolatileThread thread = new TestVolatileThread();
        thread.start();
        Thread.sleep(3000);
        thread.setFlag(false);
        Thread.sleep(1000);
        System.out.println("当前flag = " + thread.getFlag());
    }

}


class TestVolatileThread extends Thread {
//    private boolean flag = true;
    private volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ", start...");
        while(flag){

        }
        System.out.println(Thread.currentThread().getName() + ", end...");
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}