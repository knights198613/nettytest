package com.jiangwei.nettytest.testone.order.arrayorder.selectorder;

import com.jiangwei.nettytest.testone.order.arrayorder.ArrayContainer;

/**
 * Created by weijiang
 * Date: 2018/2/6
 * DESC:
 */
public class SelectOrderSort extends ArrayContainer {

    public SelectOrderSort(int size) {
        super(size);
    }

    public void selectOrderSort() {
        int min;
        for(int out=0; out<getCount()-1; out++) {
            min = out;
            for(int in=out+1; in<getCount(); in++) {
                if((Integer)getMyArray()[min] < (Integer) getMyArray()[in]) {
                    min = in;
                }
            }
            swapEle(out, min);
        }
    }
}
