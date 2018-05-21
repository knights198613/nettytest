package com.jiangwei.nettytest.testone.io.aio;

import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

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
        AsynTimeClientHandler asynTimeClientHandler = new AsynTimeClientHandler(host, port);
        new Thread(asynTimeClientHandler, "AIO-asynTimeClient_001").start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class AsynTimeClientHandler implements Runnable, CompletionHandler<Void, AsynTimeClientHandler> {

        private String host;
        private int port;
        private CountDownLatch countDownLatch;
        private AsynchronousSocketChannel asc;

        public AsynTimeClientHandler(String host, int port) {
            this.host = host;
            this.port = port;
            try {
                asc = AsynchronousSocketChannel.open();
                System.out.println("AIO Time Client has started!");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        @Override
        public void run() {
            countDownLatch = new CountDownLatch(1);
            asc.connect(new InetSocketAddress(host, port), this, this);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                asc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void completed(Void result, AsynTimeClientHandler attachment) {
            String order = "QUERY TIME ORDER";
            byte[] out = order.getBytes();
            ByteBuffer outBuffer = ByteBuffer.allocate(out.length);
            outBuffer.put(out);
            outBuffer.flip();
            asc.write(outBuffer, outBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if(attachment.hasRemaining()) {
                        //尚未发送完
                        asc.write(outBuffer, outBuffer, this);
                    }else {
                       //发完了木就读曼
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        asc.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                                attachment.flip();
                                byte[] reads = new byte[attachment.remaining()];
                                attachment.get(reads);
                                try {
                                    String body = new String(reads, "UTF-8");
                                    System.out.println("received from AIO Time Server response:"+body);
                                    countDownLatch.countDown();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {
                                exc.printStackTrace();
                                try {
                                    asc.close();
                                    countDownLatch.countDown();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();
                    try {
                        asc.close();
                        countDownLatch.countDown();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void failed(Throwable exc, AsynTimeClientHandler attachment) {
            exc.printStackTrace();
            if(asc != null) {
                try {
                    asc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
