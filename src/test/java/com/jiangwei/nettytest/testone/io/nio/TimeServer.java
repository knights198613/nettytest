package com.jiangwei.nettytest.testone.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Creator: jiang.wei
 * Date: 2018/5/18
 * DESC:
 */
public class TimeServer {

    @Test
    public void start() {

        startTimeServer();
    }

    public void startTimeServer() {
        int port = 9090;
        MutiplexServer mutiplexServer = new MutiplexServer(port);
        new Thread(mutiplexServer, "NIO-mutiplex-server-001").start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class MutiplexServer implements Runnable {
        private Selector selector;
        private ServerSocketChannel serverSocketChannel;
        private volatile boolean stop = false;

        public MutiplexServer(int port) {
            try {
                selector = Selector.open();
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("The NIO Time Server had Started! port:"+port);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

        }

        @Override
        public void run() {
            while (!stop) {
                try {
                    selector.select(1000);
                   Set<SelectionKey>  selectionKeySet = selector.selectedKeys();
                   Iterator<SelectionKey> selectionKeyIterator = selectionKeySet.iterator();
                   SelectionKey key = null;
                   while (selectionKeyIterator.hasNext()) {
                       key = selectionKeyIterator.next();
                       selectionKeyIterator.remove();
                       try {
                           handlerKey(key);
                       } catch (IOException e) {
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
                if(key.isAcceptable()) {
                   ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                   SocketChannel sc = ssc.accept();
                   sc.configureBlocking(false);
                   sc.register(selector, SelectionKey.OP_READ);
                }
                if(key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(readBuffer);
                    if(readBytes > 0) {
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        String body = new String(bytes, "UTF-8");
                        System.out.println("received from NIO Time Client request order is："+ body);
                        String currentTime = body.equalsIgnoreCase("QUERY TIME ORDER") ?
                                new Date(System.currentTimeMillis()).toString() : "BAD ORDER!";
                        doWrite(sc, currentTime);
                    }else if(readBytes < 0) { //客户端关闭
                        key.cancel();
                        sc.close();
                    }else {
                        ;     //客户端只是连接后发送了0字节内容
                    }
                }
            }
        }

        public void doWrite(SocketChannel socketChannel, String currentTime) throws IOException {
            if(currentTime != null && currentTime.length() > 0) {
                byte[] response = currentTime.getBytes();
                ByteBuffer outBuffer = ByteBuffer.allocate(response.length);
                outBuffer.put(response);
                outBuffer.flip();
                socketChannel.write(outBuffer);
            }

        }

        public void stop() {
            this.stop = true;
        }
    }
}
