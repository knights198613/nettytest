package com.jiangwei.nettytest.testone.io.bio;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Creator: jiang.wei
 * Date: 2018/5/18
 * DESC:
 */
public class TimeClinet {

    @Test
    public void start() {

        startClient();
    }

    public void startClient() {
        System.out.println("start Time Client and request Time Server");
        String order = "QUERY TIME ORDER";
        int port = 9090;
        String host = "127.0.0.1";
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("request Time Server Order is:"+order);
            out.println(order);
            String body = in.readLine();
            System.out.println("received from Time Server result is:"+body);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null) {
                out.close();
            }
            if(socket != null) {
                try {
                    socket.close();
                    socket = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
