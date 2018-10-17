package com.yearn.life.zto.httpSortResultUtil;

import com.yearn.life.zto.CommunicationConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 推送分拣结果到中通
 * Created by Vincent on 2018-10-16
 */
public class HttpSortResultClient {

    private static Logger logger = LogManager.getLogger(HttpSortResultClient.class);

    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    private ChannelFuture channelFuture;

    private Bootstrap bootstrap = new Bootstrap();

    private static URI uri;
    static {
        try {
            uri = new URI(CommunicationConfig.ztoUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                ch.pipeline().addLast(new HttpResponseDecoder());
                // 客户端发送的是httpRequest，所以要使用HttpRequestEncoder进行编码
                ch.pipeline().addLast(new HttpRequestEncoder());
                ch.pipeline().addLast(new HttpSortResultClientHandler());
            }
        });

    }

    public void sendImpl(String msg){
        try {
            channelFuture = bootstrap.connect(uri.getHost(), uri.getPort()).sync();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, HttpMethod.POST, uri.toASCIIString(),
                Unpooled.wrappedBuffer(msg.getBytes()));
        // 构建http请求
        request.headers().set(HttpHeaderNames.HOST, uri.getHost());
        request.headers().set(HttpHeaders.Names.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=utf-8");
        request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
        request.headers().set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP);
        request.headers().set(HttpHeaderNames.CONTENT_LENGTH,
                request.content().readableBytes());
        // 发送http请求
        channelFuture.channel().writeAndFlush(request).addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()){
                logger.info("send sort-result request to zto success. msg:[{}]",msg);
            }else {
                logger.info("send sort-result request to zto fail. msg:[{}]",msg);
            }
        });
        try {
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        HttpSortResultClient client = new HttpSortResultClient();
        client.start();
        client.sendImpl("{pipeline=57450-001, sortSource=995, sortTime=1534681020288, sortPortCode=102, packageCode=, turnNumber=1, billCode=261064685050, trayCode=244}");
    }

}
