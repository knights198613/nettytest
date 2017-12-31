package com.jiangwei.nettytest.testone.threadpool;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by weijiang
 * Date: 2017/12/29
 * DESC:
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        //ThreadFactory = new
        BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(10);
        ExecutorService executor = new ThreadPoolExecutor(10, 160, 0, TimeUnit.SECONDS, taskQueue);
        /*executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am working.....");
            }
        });*/
        ArrayList<Thread> taskList = new ArrayList<>();
        String msg = "I am  working .....";
        String msg2 = "I am noticeAll....";
        Task task = new Task(msg, msg2);
        for(int l=1; l<=10; l++) {
            Thread t = new Thread(task);
            t.setName("workThread_"+l);
            taskList.add(t);
        }

        for(int x=0; x<taskList.size(); x++) {
            taskList.get(x).start();
        }
    }



    static class Task implements Runnable{
        final int count = 9;
        int x = 1;
        private String msg;
        private String msg2;

        public Task(String msg, String msg2) {
            this.msg = msg;
            this.msg2 = msg2;
        }

        @Override
        public void run() {
            printTask();
        }

        public synchronized void printTask(){
            System.out.println(msg);
            if(x<=count){
                x++;
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                notifyAll();
            }

            System.out.println(msg2+"%%%%%%%%%%%%%"+Thread.currentThread().getName());
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMsg2() {
            return msg2;
        }

        public void setMsg2(String msg2) {
            this.msg2 = msg2;
        }

    }

    private static AtomicInteger count = new AtomicInteger(1);


}
