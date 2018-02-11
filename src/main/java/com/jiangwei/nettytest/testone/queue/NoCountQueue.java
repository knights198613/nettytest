package com.jiangwei.nettytest.testone.queue;

/**
 * Created by weijiang
 * Date: 2018/2/8
 * DESC:
 */
public class NoCountQueue {

    private Object[] arrays;
    private int maxsize;
    private int front;
    private int tail;

    public NoCountQueue(int size) {
        maxsize = size+1;
        arrays = new Object[maxsize];
        front = 0;
        tail = -1;
    }

    public void insert(Object t) {
        if(tail == maxsize -1) {
            tail = -1;
        }
        arrays[++tail] = t;
    }

    public Object remove() {
        Object t= arrays[front++];
        if(front == maxsize) {
            front = 0;
        }
        return t;
    }

    public Object peek() {
        return arrays[front];
    }

    public boolean isEmpty() {
        return (tail+1 == front || tail ==front+maxsize-1);
    }

    public boolean isFull() {
        return (tail== maxsize-1+front || maxsize-1-tail == front);
    }

    public int size() {
        if(tail >= front) {
            return tail-front+1;
        }else {
            return (maxsize-front)+(tail+1);
        }
    }
}
