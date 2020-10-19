package com.crossyf.dubbo.springtest.test.testWebsocket;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * 接收/处理/响应客户端websocket请求的核心业务处理类
 */
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;
    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";

    //客户端与服务端创建连接时使用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
        System.out.println("客户端与服务端连接开启...");
    }

    //客户端与服务端断开连接时使用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        System.out.println("客户端与服务端连接关闭...");
    }

    //服务端接收客户端发送过来的数据结束时使用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    // 工程出现异常时使用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    // 服务端处理客户端的核心请求时调用
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

        //客户端向服务端发送http握手请求
        if(o instanceof FullHttpRequest){
            handHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        }

        // 建立websokcet请求
        else if(o instanceof WebSocketFrame){
            handWebsocketFrame(channelHandlerContext, (WebSocketFrame) o);
        }
    }

    /**
     * 处理客户端与服务端的websocket业务
     */
    private void handWebsocketFrame(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame){
        // 是否关闭websocket的指令
        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(channelHandlerContext.channel(), ((CloseWebSocketFrame) frame).retain());
        }

        // 是否ping消息
        if(frame instanceof PingWebSocketFrame){
            channelHandlerContext.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        // 是否是二进制消息，是：抛出异常（不支持二进制消息）
        if(! (frame instanceof TextWebSocketFrame)){
            System.out.println("目前我们不支持二进制消息");
            throw  new RuntimeException("【" + this.getClass().getName() + "】不支持消息！");
        }

        // 返回应答消息
        // 获取客户端向服务端发送的消息
        String request = ((TextWebSocketFrame)frame).text();
        System.out.println("服务端收到客户端的消息===>>>" + request);

        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString()
                + channelHandlerContext.channel().id()
                + "===>>>"
                + request);

        //服务端向每个连接的客户端群发消息
        NettyConfig.group.writeAndFlush(tws);
    }


    /**
    * @Description:  处理客户端向服务端发起的http请求的业务
    * @Param: []
    * @return: void
    * @Author: caifang
    * @Date: 2020/9/21
    */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request){
        if(!request.decoderResult().isSuccess()
                || !("websocket").equals(request.headers().get("Upgrade"))){
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL,null,false);
        handshaker = wsFactory.newHandshaker(request);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else{
            handshaker.handshake(ctx.channel(), request);
        }
    }

    /**
    * @Description:  服务端向客户端响应消息
    * @Param: [ctx, request, response]
    * @return: void
    * @Author: caifang
    * @Date: 2020/9/21
    */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, DefaultFullHttpResponse response){
        //请求失败
        if(response.status().code() != 200){
            ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
        }

        // 服务端向客户端主动发送数据
        ChannelFuture f = ctx.channel().writeAndFlush(response);
        if(response.status().code() != 200){
            f.addListener(ChannelFutureListener.CLOSE); // 连接关闭
        }
    }
}
