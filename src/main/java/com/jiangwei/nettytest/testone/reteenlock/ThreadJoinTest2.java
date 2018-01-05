package com.jiangwei.nettytest.testone.reteenlock;

/**
 * Created by weijiang
 * Date: 2018/1/5
 * DESC:
 */
public class ThreadJoinTest2 {

    private Integer abc = new Integer(20);

    public static void main(String[] args) {
        ThreadJoinTest2 test2 = new ThreadJoinTest2();
        test2.doExecute();
    }

    void doExecute() {
        System.out.println(Thread.currentThread().getName()+"...........start........");
        MyThread mt = new MyThread();
        mt.start();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        abc = null;
        System.gc();
        System.out.println(Thread.currentThread().getName()+"...........end........");
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+">>>>>>>>start>>>>");
            synchronized (abc) {
                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+">>>>>>>>start>>>>");
        }
    }
}
