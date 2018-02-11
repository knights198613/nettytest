package com.jiangwei.nettytest.testone.order;

import com.jiangwei.nettytest.testone.order.arrayorder.ArrayContainer;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by weijiang
 * Date: 2018/2/6
 * DESC:
 */
public class ArrayContainerTest {

    private ArrayContainer arrayContainer;

    @Before
    public void before() {
        arrayContainer = new ArrayContainer(10);
        arrayContainer.addEle(9);
        arrayContainer.addEle(6);
        arrayContainer.addEle(2);
        arrayContainer.addEle(4);
        arrayContainer.addEle(8);
        arrayContainer.addEle(10);
        arrayContainer.addEle(1);
        arrayContainer.addEle(3);
        arrayContainer.addEle(5);
        arrayContainer.addEle(7);
        arrayContainer.display();
    }


    @Test
    public void testDeleteEle() {
        arrayContainer.deleteEle(9);
        arrayContainer.display();
    }


}
