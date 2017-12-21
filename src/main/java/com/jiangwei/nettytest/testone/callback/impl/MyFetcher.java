package com.jiangwei.nettytest.testone.callback.impl;

import com.jiangwei.nettytest.testone.callback.CallBack;
import com.jiangwei.nettytest.testone.callback.Data;
import com.jiangwei.nettytest.testone.callback.Fetcher;

/**
 * Created by weijiang
 * Date: 2017/12/19
 * DESC:
 */
public class MyFetcher implements Fetcher {

    private Data data;

    public MyFetcher(Data data) {
        this.data = data;
    }

    public void fetcherData(CallBack callBack) {
        try {
            callBack.onData(data);
        } catch (Exception e) {
            callBack.onError(e);
        }
    }
}
