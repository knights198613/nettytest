package com.jiangwei.nettytest.testone.lockcondition;

import com.jiangwei.nettytest.testone.reteenlock.ThreadJoinTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by weijiang
 * Date: 2018/1/29
 * DESC: 双端阻塞队列
 */
public class BoundQueue<T> {

    private Object[] item;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private volatile int count, addIndex, removeIndex;

    public BoundQueue(int size) {
        this.item = new Object[size];
    }


    public void addEle(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == item.length) {
                System.out.println("invoke addEle methode await thread name:"+ Thread.currentThread().getName());
                notFull.await();
            }
            item[addIndex] = t;
            if(++addIndex == item.length) {
                addIndex = 0;
            }
            count++;

            notEmpty.signal();
            System.out.println("success invoke addEle method thread name:"+ Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }


    public T removeEle() throws InterruptedException {
        lock.lock();
        T t;
        try {
            while (count == 0) {
                System.out.println("invoke removeEle methode await thread name:"+ Thread.currentThread().getName());
                notEmpty.await();
            }
            t = (T)item[removeIndex];
            if(++removeIndex == item.length) {
                removeIndex = 0;
            }
            count--;
            notFull.signal();
            System.out.println("success invoke removeELe method thread name:"+ Thread.currentThread().getName());
            return t;
        } finally {
            lock.unlock();
        }
    }


}
