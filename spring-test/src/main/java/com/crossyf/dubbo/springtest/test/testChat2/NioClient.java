package com.crossyf.dubbo.springtest.test.testChat2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/*
 *  Nio客户端
 */
public class NioClient {
    public void start(String nickName) throws IOException {
        /**
         * 创建与服务器的连接socketChannel
         */
        SocketChannel socketChannel = SocketChannel.open(
                new InetSocketAddress("127.0.0.1",8000));

        /**
         * 接收服务器端的响应
         * 新开一个线程，专门用来获取服务器端发送的响应数据
         */
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();

        /**
         * 向服务器发送数据
         */
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String request = scanner.nextLine();
            if(request != null && request.length() > 0){
                socketChannel.write(Charset.forName("UTF-8").encode(nickName + " : " + request));
            }
        }

    }
}
