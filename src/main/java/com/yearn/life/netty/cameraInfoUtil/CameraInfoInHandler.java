package com.yearn.life.netty.cameraInfoUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vincent on 2018-10-16
 */
@Service
public class CameraInfoInHandler extends ChannelInboundHandlerAdapter implements ApplicationContextAware {

    private static Logger logger = LogManager.getLogger(CameraInfoInHandler.class);

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 收到数据时调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String receive = (String) msg;
        JSONObject jsonObject = JSON.parseObject(receive);
        List<String> billCodes = (List<String>)jsonObject.get("billCodes");
    }

    @Override
    public boolean isSharable() {
        System.out.println("==============CameraGetInfoInHandler handler-sharable==============");
        return super.isSharable();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("==============CameraGetInfoInHandler channel-register==============");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("==============CameraGetInfoInHandler channel-unregister==============");
    }
    //新客户端接入
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("==============CameraGetInfoInHandler channel-Active==============");
    }
    //客户端断开
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("==============CameraGetInfoInHandler channel-inactive==============");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("==============CameraGetInfoInHandler channel-read-complete==============");
        ctx.flush();
    }

    //异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常
        logger.error(cause.getMessage());
        //关闭通道
        ctx.close();
    }

}
