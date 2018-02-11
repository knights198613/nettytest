package com.jiangwei.nettytest.testone.stack;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by weijiang
 * Date: 2018/2/6
 * DESC:
 */
public class StackXTest {

    private String testStr = "student";

    private StackX stackX;

    private char[] chars;

    @Before
    public void before() {
        chars = testStr.toCharArray();
        stackX = new StackX(chars.length);
        for(char ch : chars) {
            stackX.push(ch);
        }
    }


    @Test
    public void testRevertStr() {
        while (!stackX.isEmpty()) {
            System.out.print(stackX.pop());
        }
        System.out.println("");
    }

    @Test
    public void testIsFull() {
        System.out.println(stackX.isFull());
    }
}
