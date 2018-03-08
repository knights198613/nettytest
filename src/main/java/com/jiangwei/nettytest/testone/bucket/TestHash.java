package com.jiangwei.nettytest.testone.bucket;

/**
 * Created by Weijiang
 * Date: 2018/3/4
 * Desc: 测试Hash散列
 */
public class TestHash {

    public static void main(String[] args) {
        Integer integer = new Integer(8);
        System.out.println(Integer.toBinaryString(integer));
        integer = integer >>> 16;

        int x = -3;

        System.out.println(Integer.toBinaryString(x >> 2));
        System.out.println(x>>2);
    }


}
