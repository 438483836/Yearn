package com.yearn.life.zto.httpPortSyncUtil;

import com.alibaba.fastjson.JSON;
import com.yearn.life.pojo.SortingSchemeConfig;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Vincent on 2018-11-05
 */
public class HttpPortSyncClientHandler extends ChannelInboundHandlerAdapter implements ApplicationContextAware {

    private static Logger logger = LogManager.getLogger(HttpPortSyncClientHandler.class);

    private static StringBuilder receive = new StringBuilder();

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
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
        logger.info("==============HttpPortSyncClientHandler channel-active==============");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        receive.delete(0,receive.length());
        logger.info("==============HttpPortSyncClientHandler channel-inactive==============");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
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
                logger.info(" }END OF CONTENT");
                if (receive.length()>0){
                    Map<String,Object> getBestExpressDataMap= JSON.parseObject(receive.toString(), Map.class);

                    List<Map> PortSyncMapList = (List<Map>)getBestExpressDataMap.get("result");
                    List<SortingSchemeConfig> sortingSchemeConfigs = new ArrayList<>();

                    for (Map<String,String> map : PortSyncMapList){
                        String schemeId = map.get("id");
                        String pipeline = map.get("pipeline");
                        String sortPortCode = map.get("sortPortCode");
                        String sortPortCodeName = map.get("sortPortCodeName");
                        String siteCode = map.get("siteCode");
                        String siteName = map.get("siteName");
                        String sortMode = map.get("sortMode");
                        String sortCode = map.get("sortCode");
                        String standardSortCode = map.get("standardSortCode");
                        SortingSchemeConfig sortingSchemeConfig = new SortingSchemeConfig();
                        sortingSchemeConfig.setSchemeId(schemeId);
                        sortingSchemeConfig.setPipeline(pipeline);
                        sortingSchemeConfig.setSortPortCode(sortPortCode);
                        sortingSchemeConfig.setSortPortCodeName(sortPortCodeName);
                        sortingSchemeConfig.setSiteCode(siteCode);
                        sortingSchemeConfig.setSiteName(siteName);
                        sortingSchemeConfig.setSortMode(sortMode);
                        sortingSchemeConfig.setSortCode(sortCode);
                        sortingSchemeConfig.setStandardSortCode(standardSortCode);
                        sortingSchemeConfigs.add(sortingSchemeConfig);
                    }
                    HttpPortSyncService.initCache(sortingSchemeConfigs);


                }else {
                    logger.error("response of port-sync is empty");
                    //以为发现偶尔有返回空的情况，所以此处遇到空返回时再次请求
                }

                ctx.close();
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("==============HttpPortSyncClientHandler channel-read-complete==============");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error(cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }
}
