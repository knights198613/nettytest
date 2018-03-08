package com.jiangwei.nettytest.testone.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by Weijiang
 * Date: 2018/3/8
 * Desc: 测试软引用，弱引用，虚引用
 */
public class TestReference {

    static class BigObject {
        String name;
        int[] values;

        public BigObject(String name) {
            this.name = name;
            values = new int[1024*80];
        }
    }


    public static void main(String[] args) {
        //testStrongReference();
        //testSoftReference();
        //testSoftReference2();
        testWeakReference();
    }

    public static void testStrongReference() {
        int count = 10000;
        BigObject[] bigObjects = new BigObject[count];
        for(int i=0; i<count; i++) {
            bigObjects[i] = new BigObject("Object-"+i);
        }

        for(int x=0; x<10; x++) {
            System.out.println(bigObjects[x].name);
        }
    }

    public static void testSoftReference() {
        int count = 10000;
        SoftReference<BigObject>[] srArray = new SoftReference[count];
        for(int i=0; i<count; i++) {
            srArray[i] = new SoftReference<BigObject>(new BigObject("Object-"+i), new ReferenceQueue<BigObject>());
        }
        System.out.println(srArray[srArray.length-1].get().name);

        for(int x=0; x<10; x++) {
            System.out.println(srArray[x].get().name);
        }
    }


    public static void testSoftReference2() {
        int count = 10;
        SoftReference<BigObject>[] srArray = new SoftReference[count];
        for(int i=0; i<count; i++) {
            srArray[i] = new SoftReference<BigObject>(new BigObject("Object-"+i), new ReferenceQueue<BigObject>());
        }
        //强制进行GC
        System.gc();
        System.out.println(srArray[srArray.length-1].get().name);
        for(int x=0; x<count; x++) {
            System.out.println(srArray[x].get().name);
        }
    }


    public static void testWeakReference() {
        int count = 10;
        WeakReference<BigObject>[] wrArray = new WeakReference[count];
        for(int i=0; i<count; i++) {
            wrArray[i] = new WeakReference<BigObject>(new BigObject("Object-"+i));
        }
        System.out.println(wrArray[wrArray.length-1].get().name);
        //强制进行GC
        System.gc();
        for(int x=0; x<count; x++) {
            System.out.println(wrArray[x].get().name);
        }
    }





}

