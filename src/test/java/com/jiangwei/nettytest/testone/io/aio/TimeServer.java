package com.jiangwei.nettytest.testone.io.aio;

import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Creator: jiang.wei
 * Date: 2018/5/18
 * DESC:
 */
public class TimeServer {

    @Test
    public void start() {
       startServer();
    }

    public void startServer() {
        int port = 9090;
        AnsyTimeServer ansyTimeServer = new AnsyTimeServer(port);
        new Thread(ansyTimeServer, "AIO-ansyTimeServer_001").start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class AnsyTimeServer implements Runnable {
        private int port;
        private CountDownLatch countDownLatch;
        private AsynchronousServerSocketChannel asynchronousServerSocketChannel;

        public AnsyTimeServer(int port) {
            this.port = port;
            try {
                asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
                asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
                System.out.println("The AIO Time Server has started!");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        @Override
        public void run() {
            countDownLatch = new CountDownLatch(1);
            doAccept();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        public void doAccept() {
            asynchronousServerSocketChannel.accept(this, new AcceptCompleteHandler());
        }
    }

    class AcceptCompleteHandler implements CompletionHandler<AsynchronousSocketChannel, AnsyTimeServer> {
        @Override
        public void completed(AsynchronousSocketChannel result, AnsyTimeServer attachment) {
            attachment.asynchronousServerSocketChannel.accept(attachment, this);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            result.read(byteBuffer, byteBuffer, new ReadCompletionHandler(result));
        }

        @Override
        public void failed(Throwable exc, AnsyTimeServer attachment) {
            exc.printStackTrace();
            attachment.countDownLatch.countDown();
        }
    }

    class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

        private AsynchronousSocketChannel asc;

        public ReadCompletionHandler(AsynchronousSocketChannel asc) {
            this.asc = asc;
        }

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            attachment.flip();
            byte[] body = new byte[attachment.remaining()];
            attachment.get(body);
            try {
                String bodyStr = new String(body, "UTF-8");
                System.out.println("received AIO Time Client Order is:"+bodyStr);
                String currentTime = bodyStr.equalsIgnoreCase("QUERY TIME ORDER")?
                        new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                doWrite(currentTime);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            exc.printStackTrace();
            if(asc != null) {
                try {
                    asc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void doWrite(String response) {
            if(response != null && response.length()>0) {
                byte[] res = response.getBytes();
                ByteBuffer outBuffer = ByteBuffer.allocate(res.length);
                outBuffer.put(res);
                outBuffer.flip();
                asc.write(outBuffer, outBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        if(attachment.hasRemaining()) {
                            //还没有发送完
                            asc.write(outBuffer, outBuffer, this);
                        }
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        exc.printStackTrace();
                        try {
                            asc.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
