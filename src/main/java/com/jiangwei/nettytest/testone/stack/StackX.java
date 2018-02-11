package com.jiangwei.nettytest.testone.stack;

/**
 * Created by weijiang
 * Date: 2018/2/6
 * DESC: 模拟栈
 */
public class StackX {

    private Object[] stack;

    private int pop;

    private int maxSize;

    public StackX(int size) {
        pop = -1;
        stack = new Object[size];
        maxSize = size;
    }

    public void push(Object t) {
        stack[++pop] = t;
    }

    public Object pop() {
        return stack[pop--];
    }

    public Object peek() {
        return stack[pop];
    }

    public boolean isEmpty() {
        if(pop == -1) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if(pop == maxSize-1) {
            return true;
        }
        return false;
    }


}
