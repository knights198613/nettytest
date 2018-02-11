package com.jiangwei.nettytest.testone.order.arrayorder.poporder;

import com.jiangwei.nettytest.testone.order.arrayorder.ArrayContainer;

/**
 * Created by weijiang
 * Date: 2018/2/6
 * DESC: 冒泡排序类
 */
public class PopOrderSort extends ArrayContainer {

    public PopOrderSort(int size) {
        super(size);
    }

    public void popOrder() {
        for(int out = getCount()-1; out>1; out--) {
            for(int in=0; in<out; in++) {
                if((Integer)getMyArray()[in] > (Integer) getMyArray()[out]) {
                    swapEle(in, out);
                }
            }
        }
    }
}
