package com.jiangwei.nettytest.testone.interrupted;

import java.util.concurrent.TimeUnit;

/**
 * Created by weijiang
 * Date: 2018/1/8
 * DESC:
 */
public class InterruptedTest {
    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepRunner(), "sleepThread");
        Thread busyThread = new Thread(new BusyRunner(), "busyThread");
        sleepThread.setDaemon(true);
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is :"+sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is :"+busyThread.isInterrupted());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
           while (true)
               try {
                   TimeUnit.SECONDS.sleep(5);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                //System.out.println("sssss");
            }
        }
    }
}
