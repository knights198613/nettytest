package com.jiangwei.nettytest.testone.order.insertorder;

import com.jiangwei.nettytest.testone.order.arrayorder.ArrayContainer;
import com.jiangwei.nettytest.testone.order.arrayorder.insertorder.InsertOrderSort;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by weijiang
 * Date: 2018/2/6
 * DESC:
 */
public class InsertOrderSortTest {

    private InsertOrderSort  insertOrderSort;

    @Before
    public void before() {
        insertOrderSort = new InsertOrderSort(10);
        insertOrderSort.addEle(9);
        insertOrderSort.addEle(6);
        insertOrderSort.addEle(2);
        insertOrderSort.addEle(4);
        insertOrderSort.addEle(8);
        insertOrderSort.addEle(10);
        insertOrderSort.addEle(1);
        insertOrderSort.addEle(3);
        insertOrderSort.addEle(5);
        insertOrderSort.addEle(7);
        insertOrderSort.display();
    }

    @Test
    public void testInsertOrderSort() {
        insertOrderSort.inserOrderSort();
        insertOrderSort.display();
    }


}
