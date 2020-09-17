package com.crossyf.dubbo.springtest.test.testChat2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
* Nio客户端
*/ 
public class NioServer {

    /**
     * 启动方法
     */
    public void start() throws IOException {

        /**
         * 1、创建selector
         */
        Selector selector = Selector.open();

        /**
         * 2、创建channel
         */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        /**
         * 3、绑定监听端口
         */
        serverSocketChannel.bind(new InetSocketAddress(8000));

        /**
         * 4、设置通道为非阻塞模式
         */
        serverSocketChannel.configureBlocking(false);

         /**
         * 5、注册channel到selector上
         */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务端启动成功");

         /**
         * 6、循环等待接入：调用select方法检查就绪状态下的通道
         */
        for (;;){  // while(true)

            int readyChannels = selector.select();

            if(readyChannels == 0) continue; // 防止空

            /**
             * 获取就绪状态下的channel属性轮询占用cpu资源
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator iterator = selectionKeys.iterator();

            while(iterator.hasNext()){

                /**
                 * SelectionKey的实例
                 */
                SelectionKey selectionKey = (SelectionKey) iterator.next();

                /**
                 * selector每次监听都是监听所有的channel，每次监听都会set进入，所以每次操作需要移除
                 */
                iterator.remove();

                /**
                 * 7、根据channel属性处理不同的业务
                 */

                /**
                 * 接入事件处理
                 */
                if(selectionKey.isAcceptable()){
                    acceptHandler(serverSocketChannel, selector);
                }

                /**
                 * 可读事件处理
                 */
                if(selectionKey.isReadable()){
                    readHandler(selectionKey, selector);
                }
            }

        }

    }

    /**
     * 接入事件处理器
     */
    private void acceptHandler(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        /**
         * 从serverSocketChannel中获取到接入事件，创建socketchannel
         */
        SocketChannel socketChannel = serverSocketChannel.accept();

        /**
         * socketchannel设置非阻塞模式
         */
        socketChannel.configureBlocking(false);

        /**
         * socketchannel注册到selector上，监听可读事件
         */
        socketChannel.register(selector, SelectionKey.OP_READ);

        /**
         * 回复客户端提示信息  编码
         */
        socketChannel.write(Charset.forName("UTF-8").encode("您与聊天室中的其他人都不是朋友关系，请注意保护隐私"));
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
            /*System.out.println( ":: " + request);*/
            broadCast(selector, socketChannel, request);
        }
    }

    public void broadCast(Selector selector, SocketChannel socketChannel, String request){
        /**
         * 获取所有的channel
         */
        Set<SelectionKey> selectionKeys = selector.keys();

        selectionKeys.forEach(selectionKey -> {
            /**
             * 向所有的channel发送消息
             */
            Channel targetChannel = selectionKey.channel();
            if(targetChannel instanceof SocketChannel &&
                    targetChannel != socketChannel){
                // 剔除发消息的客户端
                try {
                    ((SocketChannel) targetChannel).write(Charset.forName("UTF-8").encode(request));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 主方法
     * @param args
     */
    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.start();
    }
}
