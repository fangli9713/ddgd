package com.fangln.dd.init.netty.client;

import com.fangln.dd.init.netty.ServerHandler;
import com.fangln.dd.init.netty.dto.BaseResultOuterClass;
import com.googlecode.protobuf.format.JsonFormat;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Fangln on 2019/1/30.
 */
public class NettyClientProtoBufHandler  extends SimpleChannelInboundHandler<BaseResultOuterClass.BaseResult> {

    private static Logger log = LoggerFactory.getLogger(ServerHandler.class);
    private int count = 0;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        //System.out.println("ChatClient:" + incoming.remoteAddress() + "上线");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseResultOuterClass.BaseResult ret)
            throws Exception {
        //消息会在这个方法接收到，msg就是经过解码器解码后得到的消息，框架自动帮你做好了粘包拆包和解码的工作
       // System.out.println("服务器返回的data数据==="+ JsonFormat.printToString(ret));
        handle(ctx,ret);
    }

    public void handle(ChannelHandlerContext ctx, BaseResultOuterClass.BaseResult msg) throws Exception{

        System.out.println(JsonFormat.printToString(msg));
    }
}
