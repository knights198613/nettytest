package com.jiangwei.nettytest.testone.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Creator: jiang.wei
 * Date: 2018/5/18
 * DESC:
 */
public class TimeClient {

    @Test
    public void start() {
        startClient();

    }

    public void startClient() {
        String host = "127.0.0.1";
        int port = 9090;
        new Thread(new TimeClientHandler(port, host), "NIO-timeClient-001").start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class TimeClientHandler implements Runnable {
        private int port;
        private String host;
        private SocketChannel socketChannel;
        private Selector selector;
        private volatile boolean stop = false;

        public TimeClientHandler(int port, String host) {
            this.port = port;
            this.host = host;
            try {
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
                selector = Selector.open();
                System.out.println("the NIO Time Client has Started!");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

        }

        @Override
        public void run() {
            try {
                doConnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!stop) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeySet.iterator();
                    SelectionKey key = null;
                    while (it.hasNext()) {
                        key = it.next();
                        it.remove();
                        try {
                            handlerKey(key);
                        } catch (Exception e) {
                            e.printStackTrace();
                            if(key != null) {
                                key.cancel();
                                if(key.channel() != null) {
                                    key.channel().close();
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


        public void handlerKey(SelectionKey key) throws IOException {
            if(key.isValid()) {
                SocketChannel sc = (SocketChannel) key.channel();
                if(key.isConnectable()) {
                    if(sc.finishConnect()) {
                        sc.register(selector, SelectionKey.OP_READ);
                        doWrite(sc);
                    }else {
                        //建联失败，退出
                        System.exit(1);
                    }
                }
                if(key.isReadable()) {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(readBuffer);
                    if(readBytes > 0) {
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        String body = new String(bytes, "UTF-8");
                        System.out.println("received from NIO Time Server response:"+body);
                        this.stop = true;
                    }else if(readBytes < 0) {
                       //server端关闭
                        key.cancel();
                        key.channel().close();
                    }else {
                        //接收了0个字节
                        ;
                    }
                }
            }
        }


        public void doConnect() throws IOException {
            if(socketChannel.connect(new InetSocketAddress(host, port))) { //直接连接成功发送请求，注册读事件
                socketChannel.register(selector, SelectionKey.OP_READ);
                doWrite(socketChannel);
            }else {
                //没连接成功，注册连接事件
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        }

        public void doWrite(SocketChannel socketChannel) throws IOException {
            String order = "QUERY TIME ORDER";
            System.out.println("Send order to NIO Time Server, order is:"+order);
            byte[] bytes = order.getBytes();
            ByteBuffer outBuffer = ByteBuffer.allocate(bytes.length);
            outBuffer.put(bytes);
            outBuffer.flip();
            socketChannel.write(outBuffer);
            if (!outBuffer.hasRemaining()) {
                System.out.println("Send order to NIO Time Server Success!");
            }
        }


        public void stop() {
            this.stop = true;
        }
    }

}
