package com.jiangwei.nettytest.testone.classloader;

/**
 * Created by weijiang
 * Date: 2018/1/9
 * DESC:
 */
public class Cat {
    private int age;
    private String name;

    public Cat(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("Cat name is:"+ name+", and cat age is:"+age);
    }
}
