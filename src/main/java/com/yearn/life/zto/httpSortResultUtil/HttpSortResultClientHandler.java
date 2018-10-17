package com.yearn.life.zto.httpSortResultUtil;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Vincent on 2018-10-16
 */
public class HttpSortResultClientHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LogManager.getLogger(HttpSortResultClientHandler.class);

    private static StringBuilder receive = new StringBuilder();


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("==============HttpSortResultClientHandler channel-active==============");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        receive.delete(0,receive.length());
        logger.info("==============HttpSortResultClientHandler channel-inactive==============");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;

            logger.info("STATUS: " + response.getStatus());
            logger.info("VERSION: " + response.getProtocolVersion());

            if (!response.headers().isEmpty()) {
                for (String name: response.headers().names()) {
                    for (String value: response.headers().getAll(name)) {
                        logger.info("HEADER: " + name + " = " + value);
                    }
                }
            }

            if (HttpHeaders.isTransferEncodingChunked(response)) {
                logger.info("CHUNKED CONTENT {");
            } else {
                logger.info("CONTENT {");
            }
        }
        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            String response = content.content().toString(CharsetUtil.UTF_8);

            receive.append(response);

            if (content instanceof LastHttpContent) {
                logger.info(receive.toString());
                if (receive.length()>0){
                    JSONObject jsonObject = JSONObject.parseObject(receive.toString());
                    logger.info(" }END OF CONTENT");

                }else {
                    logger.error("response of sort-result is empty");
                    //以为发现偶尔有返回空的情况，所以此处遇到空返回时再次请求
                }

                ctx.close();
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("==============HttpSortResultClientHandler channel-read-complete==============");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error(cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }

}
