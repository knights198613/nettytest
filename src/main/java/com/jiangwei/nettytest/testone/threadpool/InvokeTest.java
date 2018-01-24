package com.jiangwei.nettytest.testone.threadpool;

import com.jiangwei.nettytest.testone.Invoke;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by weijiang
 * Date: 2018/1/24
 * DESC:
 */
public class InvokeTest {
    public static void main(String[] args) {
        Src src = new Src();
        src.setBytesTotal(1024);
        src.setCarrierId(2);
        src.setCdnId(3);
        src.setConnectTime(100);
        src.setConnectTypeId(4);
        src.setCountryId(5);
        src.setDnsTime(200);
        src.setFirstPacketTime(300);
        src.setHostId(6);
        src.setHostIp(7);
        src.setManufacturerId(9);
        src.setManufacturerModelId(10);
        src.setMobileAppId(11);
        src.setMobileAppVersionId(12);
        src.setOsId(13);
        src.setOsVersionId(14);
        src.setRegionId(15);
        src.setResponseTime(400);
        src.setSslTime(500);
        src.setTimestamp(600);
        src.setUriId(16);

        Target target = new Target();
        Target target1 = new Target();


        long startTime1 = System.currentTimeMillis();
        Invoke.copyData(src, target);
        System.out.println("方法调用耗时1："+(System.currentTimeMillis()-startTime1)+"ms");

        long startTime2 = System.currentTimeMillis();
        Invoke.copyData(src, target);
        System.out.println("方法调用耗时2："+(System.currentTimeMillis()-startTime2)+"ms");

        try {
            long startTime = System.currentTimeMillis();
            BeanUtils.copyProperties(src, target1);
            System.out.println("使用BeanUtils用时："+(System.currentTimeMillis()-startTime)+"ms");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(target.toString());
    }
}
