package com.jiangwei.nettytest.testone.queue;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by weijiang
 * Date: 2018/2/8
 * DESC:
 */
public class NoCountQueueTest {

    private NoCountQueue queue;

    @Before
    public void before() {
        queue = new NoCountQueue(10);
    }


    @Test
    public void testInsert() {
        queue.insert(0);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        queue.insert(6);
        queue.insert(7);
        queue.insert(8);
        queue.insert(9);
        queue.insert(10);
        //queue.insert(11);

        System.out.println(queue.isFull());

    }
}
