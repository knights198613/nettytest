package com.jiangwei.nettytest.testone.futrues;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by weijiang
 * Date: 2017/12/21
 * DESC:
 */
public class MyFutureExample {

    public static void main(String[] args) throws Exception {
        Runnable task1 = new Runnable() {
            public void run() {
                System.out.println("I'm task1 is doing..........!");
            }
        };

        Callable<Integer> task2 = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Integer(1000);
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<?> future1 = executorService.submit(task1);

        Future<Integer> future2 = executorService.submit(task2);

        System.out.println("task1 is completed ? " + future1.isDone());
        System.out.println("task2 is completed ? " + future2.isDone());

        while (future1.isDone()) {
            System.out.println("task1 is completed!");
            break;
        }

        while (future2.isDone()) {
            System.out.println("return value of task2 is:"+future2.get());
            break;
        }

    }
}
