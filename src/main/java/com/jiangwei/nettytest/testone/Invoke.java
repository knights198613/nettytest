package com.jiangwei.nettytest.testone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by weijiang
 * Date: 2018/1/24
 * DESC:
 */
public class Invoke {
    public static final Logger LOGGER = LoggerFactory.getLogger(Invoke.class);

    public static void copyData(Object src, Object target) {
        long startTime = System.currentTimeMillis();
       Class srcCls = src.getClass();
       Class targetCls = target.getClass();

       Field[] targetFields = targetCls.getDeclaredFields();

        try {
            for(Field field : targetFields) {
                String fieldName = field.getName();
                String firstUpperName = fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
                String setMethodName = "set"+firstUpperName;
                String getMethodName = field.getType() == boolean.class ? "is"+firstUpperName : "get"+firstUpperName;
                Method getMethod = srcCls.getDeclaredMethod(getMethodName);
                Method setMethod = targetCls.getDeclaredMethod(setMethodName, field.getType());
                Object value = getMethod.invoke(src, null);
                setMethod.invoke(target, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("auto set field error: src#" + srcCls.toString() + ", target#" + targetCls.toString() + ", msg#" + e.getMessage(), e);
        }

        System.out.println("用时："+(System.currentTimeMillis() - startTime)+"ms");
    }





}
