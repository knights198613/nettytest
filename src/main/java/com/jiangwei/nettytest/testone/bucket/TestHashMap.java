package com.jiangwei.nettytest.testone.bucket;

import java.util.HashMap;

/**
 * Created by Weijiang
 * Date: 2018/3/4
 * Desc: hashMap测试及代码调试
 */
public class TestHashMap {

    public static void main(String[] args) {
        HashMap<Object, Object> hashMap = new HashMap<>(1, 0.75f);

        hashMap.put(1, 2);
        hashMap.put(3, 4);

        System.out.println(hashMap.size());

        System.out.println(-1 & new Integer(30).hashCode());
    }

}
