package com.crossyf.dubbo.springtest.test.testChat2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioClientHandler implements Runnable{

    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        /**
         * 6、循环等待接入：调用select方法检查就绪状态下的通道
         */
        try {
            for (; ; ) {
                int readyChannels = selector.select();

                if (readyChannels == 0) continue;

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {

                    SelectionKey selectionKey = (SelectionKey) iterator.next();

                    /**
                     * selector每次监听都是监听所有的channel，每次监听都会set进入，所以每次操作需要移除
                     */
                    iterator.remove();

                    /**
                     * 可读事件处理
                     */
                    if (selectionKey.isReadable()) {
                        readHandler(selectionKey, selector);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 读写事件处理器
     */
    private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
            /**
             * 从SelectionKeys中获取到就绪的事件和属性
             */
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            /**
             * 创建可读写的buffer
             */
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            /**
             * 循环读取客户端的请求信息
             */
            String request = "";
            while(socketChannel.read(byteBuffer) > 0){
                /**
                 * 切换buffer为读模式
                 */
                byteBuffer.flip();

                /**
                 * 读取buffer的内容 解码
                 */
                request += Charset.forName("UTF-8").decode(byteBuffer);
            }

            /**
             * 处理之后，将本channel再次注册到selector上，监听其他可读事件（之前都被移除了）
             */

            socketChannel.register(selector, SelectionKey.OP_READ);

            /**
             * 将客户端发送的请求信息，广播给其他客户端（聊天室）
             */

            if(request.length() > 0){
                System.out.println( request);
            }
        }
}
