package com.jiangwei.nettytest.testone.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by weijiang
 * Date: 2017/12/29
 * DESC:
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am working.....");
            }
        });
    }
}
