package com.jiangwei.nettytest.testone.io.bio;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Creator: jiang.wei
 * Date: 2018/5/18
 * DESC:
 */
public class TimeServer {

    @Test
    public void start() {
      serverStart();
    }

    public void serverStart() {
        int port = 9090;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("the Time Server has started at port:"+ port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new TimeHandler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket != null) {
                System.out.println("the Time Server becoming close....");
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverSocket = null;
            }
        }
    }


    class TimeHandler implements Runnable {
        private Socket socket;

        public TimeHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader in = null;
            PrintWriter out = null;
            try {

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String body = null;
                String currentTime = null;
                while (true) {
                    body = in.readLine();
                    if(body != null)
                        break;
                }
                System.out.println("received from Time Client request order is :"+ body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        new Date(System.currentTimeMillis()).toString() : "BAD ORDEAR!";
                System.out.println("reply Time Client result is:"+currentTime);
                out.println(currentTime);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
