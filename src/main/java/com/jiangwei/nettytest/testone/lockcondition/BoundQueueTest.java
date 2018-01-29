package com.jiangwei.nettytest.testone.lockcondition;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by weijiang
 * Date: 2018/1/29
 * DESC: 测试双端阻塞队列
 */
public class BoundQueueTest {
    private BoundQueue<Integer> boundQueue = new BoundQueue<>(20);

    public static void main(String[] args) {
       BoundQueueTest  test = new BoundQueueTest();
       test.doJob();
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doJob() {
        for(int x=20; x>0; x--) {
            Thread thread = new Thread(new RunnerAdd(x), "tAddEle_"+x);
            thread.start();
        }

        for(int y=20; y>0; y--) {
            Thread thread = new Thread(new RunnerRemove(y), "tRemoveEle_"+y);
            thread.start();
        }
    }



    class RunnerAdd implements Runnable {
        private int sleepTime;

        public RunnerAdd(int sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(sleepTime)*1000);
                boundQueue.addEle(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    class RunnerRemove implements Runnable {
        private int sleepTime;

        public RunnerRemove(int sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(sleepTime)*1000);
                Integer result = boundQueue.removeEle();
                System.out.println("删除结果为："+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
