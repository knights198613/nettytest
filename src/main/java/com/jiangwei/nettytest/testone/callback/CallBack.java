package com.jiangwei.nettytest.testone.callback;

/**
 * Created by weijiang
 * Date: 2017/12/19
 * DESC: 回调接口
 */
public interface CallBack {

    public void onData(Data data);

    public void onError(Throwable cause);
}
