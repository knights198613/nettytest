package com.jiangwei.nettytest.testone.reteenlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by weijiang
 * Date: 2018/1/23
 * DESC:
 */
public class RetrantLookTest2 {
    static ReentrantLock lock = new ReentrantLock();

    static int count = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner(), "t1");
        Thread t2 = new Thread(new Runner(), "t2");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }

    static class Runner implements Runnable {
        @Override
        public void run() {
          doAdd();
        }
    }

    static void doAdd() {
        lock.lock();
        try {
            count++;
            TimeUnit.SECONDS.sleep(4);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
