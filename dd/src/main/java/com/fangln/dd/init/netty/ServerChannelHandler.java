package com.fangln.dd.init.netty;


import com.fangln.dd.init.netty.dto.BaseMsgOuterClass;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

public class ServerChannelHandler extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch){
        ChannelPipeline pipeline = ch.pipeline();
        
     // ----Protobuf处理器，这里的配置是关键----  
        pipeline.addLast(new ProtobufVarint32FrameDecoder());// 用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）  
        //配置Protobuf解码处理器，消息接收到了就会自动解码，ProtobufDecoder是netty自带的，Message是自己定义的Protobuf类  
        pipeline.addLast(
                new ProtobufDecoder(BaseMsgOuterClass.BaseMsg.getDefaultInstance()));
        // 用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。  
        pipeline.addLast(
                new ProtobufVarint32LengthFieldPrepender());  
        //配置Protobuf编码器，发送的消息会先经过编码  
        pipeline.addLast( new ProtobufEncoder());  
        // ----Protobuf处理器END----  
        pipeline.addLast( new ServerHandler());//自己定义的消息处理器，接收消息会在这个类处理  
        pipeline.addLast( new IdleStateHandler(5, 0, 0,
                TimeUnit.SECONDS));// //此两项为添加心跳机制,60秒查看一次在线的客户端channel是否空闲  
        pipeline.addLast(new HeartBeatServerHandler());// 心跳处理handler  
        //pipeline.addLast(new ReadTimeoutHandler(20));
    }
}
