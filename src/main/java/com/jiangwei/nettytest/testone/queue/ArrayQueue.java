package com.jiangwei.nettytest.testone.queue;

/**
 * Created by weijiang
 * Date: 2018/2/7
 * DESC:
 */
public class ArrayQueue {

    private Object[] queue;
    private int fronter;
    private int tail;
    private int maxsize;
    private int elements;

    public ArrayQueue(int size) {
        queue = new Object[size];
        maxsize = size;
        fronter = 0;
        tail = -1;
        elements = 0;
    }


    public void enque(Object t) {
        if(tail == maxsize-1) {
            tail = -1;
        }
        queue[++tail] = t;
        elements++;
    }

    public Object deque() {
        if(fronter == maxsize) {
            fronter = 0;
        }
        Object t = null;
        t = queue[fronter++];
        elements--;
        return t;
    }


    public Object peek() {
        return queue[fronter];
    }

    public boolean isEmpty() {
        if(elements == 0)
            return true;
        return false;
    }

    public boolean isFull() {
        if(elements == maxsize)
            return true;
        return false;
    }
}
