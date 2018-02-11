package com.jiangwei.nettytest.testone.order.poporder;

import com.jiangwei.nettytest.testone.order.arrayorder.ArrayContainer;
import com.jiangwei.nettytest.testone.order.arrayorder.poporder.PopOrderSort;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by weijiang
 * Date: 2018/2/6
 * DESC:
 */
public class PopOrderSortTest {

    private PopOrderSort popOrderSort;

    @Before
    public void before() {
        popOrderSort = new PopOrderSort(10);
        popOrderSort.addEle(9);
        popOrderSort.addEle(6);
        popOrderSort.addEle(2);
        popOrderSort.addEle(4);
        popOrderSort.addEle(8);
        popOrderSort.addEle(10);
        popOrderSort.addEle(1);
        popOrderSort.addEle(3);
        popOrderSort.addEle(5);
        popOrderSort.addEle(7);
        popOrderSort.display();
    }

    @Test
    public void testPopOrder() {
        popOrderSort.popOrder();
        popOrderSort.display();
    }


}
