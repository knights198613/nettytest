package com.jiangwei.nettytest.testone.sych;

/**
 * Created by weijiang
 * Date: 2018/1/9
 * DESC:
 */
public class Sychornized {
    public static void main(String[] args) {
        synchronized (Sychornized.class) {

        }
        man();
    }

    public static synchronized void man() {

    }
}
