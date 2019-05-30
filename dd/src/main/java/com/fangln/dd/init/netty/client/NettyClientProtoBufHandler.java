package com.fangln.dd.init.netty.client;

import com.fangln.dd.init.netty.dto.BaseMsgOuterClass;
import com.fangln.dd.init.netty.dto.BaseResultOuterClass;
import com.googlecode.protobuf.format.JsonFormat;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
  * @description: 客户端处理类
  * @author fanglinan
  * @date 2019/5/30
  */
public class NettyClientProtoBufHandler  extends ChannelInboundHandlerAdapter {

    final private Random random = new Random();
    final private int baseRandom = 5;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        super.channelActive(ctx);
       // this.channel = ctx.channel();
        //发送心跳包
        ping(ctx.channel());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception{
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.WRITER_IDLE) {
                // write heartbeat to server
                ctx.writeAndFlush(NettyClientProtoBufHandler.heartMsg());
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj)
            throws Exception {
        BaseResultOuterClass.BaseResult msg = ( BaseResultOuterClass.BaseResult)obj;

        //消息会在这个方法接收到，msg就是经过解码器解码后得到的消息，框架自动帮你做好了粘包拆包和解码的工作
        System.out.println("服务器返回的data数据==="+ new JsonFormat().printToString(msg));
        handle(ctx,msg);
    }

    private void ping(Channel channel) {
        int second = Math.max(5, random.nextInt(baseRandom));
        System.out.println("next heart beat will send after " + second + "s.");
        ScheduledFuture<?> future = channel.eventLoop().schedule(()-> {
                if (channel.isActive()) {
                    System.out.println("sending heart beat to the server...");
                    channel.writeAndFlush(NettyClientProtoBufHandler.heartMsg());
                } else {
                    System.err.println("The connection had broken, cancel the task that will send a heart beat.");
                    channel.closeFuture();
                    throw new RuntimeException();
                }
        }, second, TimeUnit.SECONDS);

        future.addListener(s-> {
            if (s.isSuccess()) {
                ping(channel);
            }
        });
    }

    /**
     * 对服务端下发的数据进行处理的方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    public void handle(ChannelHandlerContext ctx, BaseResultOuterClass.BaseResult msg) throws Exception{
        System.out.println(new JsonFormat().printToString(msg));

        final String method = msg.getMethod();
        if(method.equals("heart") ){
            ctx.writeAndFlush(heartMsg());
        }
    }

    public static BaseMsgOuterClass.BaseMsg.Builder heartMsg(){
        BaseMsgOuterClass.BaseMsg.Builder baseMsg = BaseMsgOuterClass.BaseMsg.newBuilder();
        baseMsg.setMethod("heart");
        return baseMsg;
    }
}
