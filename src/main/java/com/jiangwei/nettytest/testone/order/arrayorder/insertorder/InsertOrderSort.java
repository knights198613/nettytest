package com.jiangwei.nettytest.testone.order.arrayorder.insertorder;

import com.jiangwei.nettytest.testone.order.arrayorder.ArrayContainer;

/**
 * Created by weijiang
 * Date: 2018/2/6
 * DESC:
 */
public class InsertOrderSort extends ArrayContainer {

    public InsertOrderSort(int size) {
        super(size);
    }


    public void inserOrderSort() {
        int temp;
        int in;
        for(int out=1; out < getCount(); out++) {
            temp = (Integer) getMyArray()[out];
            in = out;
            while (in>0 && (Integer)getMyArray()[in-1]>temp) {
                getMyArray()[in] = getMyArray()[in-1];
                --in;
            }

            getMyArray()[in] = temp;
        }
    }
}
