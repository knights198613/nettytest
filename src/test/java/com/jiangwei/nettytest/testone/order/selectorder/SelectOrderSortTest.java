package com.jiangwei.nettytest.testone.order.selectorder;

import com.jiangwei.nettytest.testone.order.arrayorder.selectorder.SelectOrderSort;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by weijiang
 * Date: 2018/2/6
 * DESC:
 */
public class SelectOrderSortTest {
    
    private SelectOrderSort selectOrderSort;
    
    @Before
    public void before() {
        selectOrderSort = new SelectOrderSort(10);
        selectOrderSort.addEle(9);
        selectOrderSort.addEle(6);
        selectOrderSort.addEle(2);
        selectOrderSort.addEle(4);
        selectOrderSort.addEle(8);
        selectOrderSort.addEle(10);
        selectOrderSort.addEle(1);
        selectOrderSort.addEle(3);
        selectOrderSort.addEle(5);
        selectOrderSort.addEle(7);
        selectOrderSort.display();
    }


    @Test
    public void testSelectOrderSort() {
        selectOrderSort.selectOrderSort();
        selectOrderSort.display();
    }
}
