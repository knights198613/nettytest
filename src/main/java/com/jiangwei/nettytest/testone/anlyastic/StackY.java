package com.jiangwei.nettytest.testone.anlyastic;

/**
 * Created by weijiang
 * Date: 2018/2/11
 * DESC: 辅助解析后缀表达式的栈
 */
public class StackY {


    private char[] chars;
    private int pop;
    private int nElements;

    public StackY(int size) {
        chars = new char[size];
        pop = -1;
        nElements = 0;
    }


    public void push(char ch) {
        chars[++pop] = ch;
        nElements++;
    }

    public char pop() {
        char ch = chars[pop--];
        nElements--;
        return ch;
    }

    public boolean isEmpty() {
        if(pop == -1)
            return true;
        return false;
    }

    public boolean isFull() {
        if(pop == nElements-1)
            return true;
        return false;
    }

    public char peek() {
        return chars[pop];
    }

    public char peekN(int n) {
        return chars[n];
    }

    public int size() {
        return nElements;
    }

    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("Stack {bottom-->top}:");
        for(int j=0; j< this.size(); j++) {
            System.out.print(this.peekN(j)+" ");
        }
        System.out.println();
    }

}
