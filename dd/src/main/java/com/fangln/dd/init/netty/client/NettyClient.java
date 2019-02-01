package com.fangln.dd.init.netty.client;

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
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

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
                sc.pipeline().addLast(new NettyClientProtoBufInitializer()); //客户端业务处理类
            }
        });
    }
    public void connect(){
        try {
            this.cf = b.connect("10.1.3.32", PORT).sync();
            System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss") +"远程服务器已经连接, 可以进行数据交换..");

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
}
