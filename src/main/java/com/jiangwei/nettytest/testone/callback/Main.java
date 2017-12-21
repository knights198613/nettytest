package com.jiangwei.nettytest.testone.callback;

import com.jiangwei.nettytest.testone.callback.impl.MyFetcher;

/**
 * Created by weijiang
 * Date: 2017/12/19
 * DESC: 启动主程序
 */
public class Main {

    public static void main(String[] args) {
        Fetcher fetcher = new MyFetcher(new Data(7, 9));

        fetcher.fetcherData(new CallBack() {
            public void onData(Data data) {
                System.out.println("data = [" + data.getM()+","+data.getN() + "]");
            }

            public void onError(Throwable cause) {
                System.out.println("cause = [" + cause.getMessage() + "]");
            }
        });
    }
}
