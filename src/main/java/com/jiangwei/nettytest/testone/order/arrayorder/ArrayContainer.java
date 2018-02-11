package com.jiangwei.nettytest.testone.order.arrayorder;

/**
 * Created by weijiang
 * Date: 2018/2/6
 * DESC: 父数组容器
 */
public class ArrayContainer {

    private Object[] myArray;

    private int count;



    public ArrayContainer(int size) {
        myArray = new Object[size];
        this.count = 0;
    }


    public void addEle(Object t) {
        myArray[count] = t;
        ++count;
    }

    public Object deleteEle(Object t) {
        Object result = null;
        int tempIndex = -1;
        for(int index=0; index<count-1; index++) {
            if(myArray[index].equals(t)) {
                tempIndex = index;
                result = myArray[index];
                break;
            }
        }
        if(tempIndex > -1) {
            for(int delIndex=0; delIndex<count-1; delIndex++) {
                myArray[delIndex] = myArray[delIndex+1];
            }
            --count;
        }
        return result;
    }

    public void swapEle(int one, int two) {
        Object temp = myArray[one];
        myArray[one] = myArray[two];
        myArray[two] = temp;
    }


    public void display() {
        System.out.print("数组内容：");
        for(int index=0; index < count-1; index++) {
            System.out.print(myArray[index]+",");
        }
        System.out.print(myArray[count-1]+"\n");
    }


    public Object[] getMyArray() {
        return myArray;
    }

    public void setMyArray(Object[] myArray) {
        this.myArray = myArray;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
