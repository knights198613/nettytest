package com.jiangwei.nettytest.testone.classloader;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by weijiang
 * Date: 2018/1/9
 * DESC:
 */
public class MyClassLoader extends ClassLoader {

    private static final String driver = "D:/";
    private static final String fileType = ".class";

    static {
        System.out.println("MyClassLoader.....");
    }



    public Class<?> renderClass(String name) {
        byte[] data = loadClassData(name);
        return this.defineClass(null, data, 0, data.length);
    }

    public byte[] loadClassData(String name) {
        byte[] data = new byte[1024];
        int ch = 0;
        File classFile = new File(driver+name+fileType);
        ByteOutputStream bos = new ByteOutputStream();
        try {
            FileInputStream fs = new FileInputStream(classFile);
            try {
                while ((ch = fs.read(data, 0, data.length)) != -1) {
                    bos.write(data);
                    //data = new byte[1024];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("class is:"+new String(bos.getBytes(), Charset.forName("UTF-8")));
        return bos.getBytes();
    }

    public static void main(String[] args) {
        MyClassLoader loader = new MyClassLoader();
        Class<?> clazz = loader.renderClass("Cat");
        System.out.println(clazz.getName());
    }
}
