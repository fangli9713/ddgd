package com.fangln.dd.init.netty.client;

import com.fangln.dd.init.netty.dto.BaseMsgOuterClass;
import com.fangln.dd.init.netty.dto.BaseResultOuterClass;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.commons.lang3.time.FastDateFormat;

import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Fangln on 2019/1/30.
 */
public class NettyClient {

    protected final int PORT = 8031;

    private static class SingletonHolder {
        static final NettyClient instance = new NettyClient();
    }

    public static NettyClient getInstance() {
        return SingletonHolder.instance;
    }

    private EventLoopGroup group;
    private Bootstrap b;
    public ChannelFuture cf;

    public NettyClient() {
        group = new NioEventLoopGroup();
        b = new Bootstrap();
        b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,1000).
                group(group).
                channel(NioSocketChannel.class).
                handler(new LoggingHandler(LogLevel.INFO)).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                //客户端业务处理类
                sc.pipeline().addLast(new NettyClientProtoBufInitializer());
            }
        });
    }
    public void connect(){
        try {
            this.cf = b.connect("192.168.3.45", PORT).sync();
            System.out.println(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()) +"远程服务器已经连接, 可以进行数据交换..");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ChannelFuture getChannelFuture(){
        //如果管道没有被开启或者被关闭了，那么重连
        if(this.cf == null){
            this.connect();
        }
        if(!this.cf.channel().isActive()){
            this.connect();
        }
        return this.cf;
    }

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        final ChannelFuture channelFuture = client.getChannelFuture();
        BaseMsgOuterClass.BaseMsg.Builder builder = BaseMsgOuterClass.BaseMsg.newBuilder();
        builder.setToken("111111111");
        channelFuture.channel().writeAndFlush(builder);

    }
}
