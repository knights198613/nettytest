package com.jiangwei.nettytest.testone.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Weijiang
 * Date: 2017/12/31
 * Desc:
 */
public class ThreadStateTest {

    public static void main(String[] args) {
        ThreadStateTest test = new ThreadStateTest();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
               test.task();
            }
        });

        t.setName("thread_t");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.task();
            }
        });
        t1.setName("thread_t1");

        t.start();
        t1.start();

        try {
            t.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void task(){
        for(int x=0; x<1000000; x++) {
            System.out.println(Thread.currentThread().getName()+", x="+x);
        }

        try {
            while (switcher.incrementAndGet() == 1) {
                wait();
                break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
        for(int y=0; y<1000000; y++) {
            System.out.println(Thread.currentThread().getName()+", y="+y);
        }
    }

    private static AtomicInteger switcher = new AtomicInteger(0);
}
