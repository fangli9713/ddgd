package com.fangln.dd.controller;

import com.fangln.dd.init.netty.client.NettyClient;
import com.fangln.dd.init.netty.dto.BaseMsgOuterClass;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Fangln on 2019/1/30.
 */
public class SocketTest {
    public static void main(String[] args) {


        for(int i = 0; i <1; i++){
            final int j = i;
            new Thread(()->{
                NettyClient c = new NettyClient();
                c.connect();
                while (true){
                    final ChannelFuture cf = c.getChannelFuture();
                    Channel channel = cf.channel();
                    if(channel.isActive() && channel.isOpen()){
                       // System.out.println(j+"=======isActive=="+channel.isActive());
                    }else{
                       // System.err.println(j+"=======isActive=="+channel.isActive());
                    }
                    if(!channel.isActive()){
                        ChannelFuture channelFuture = c.getChannelFuture();
                        channel = channelFuture.channel();
                    }
                    channel.writeAndFlush(msg());
                    //System.out.println("发送了一条数据");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static BaseMsgOuterClass.BaseMsg.Builder msg(){
        BaseMsgOuterClass.BaseMsg.Builder msg = BaseMsgOuterClass.BaseMsg.newBuilder();
        msg.setMethod("uploadEnterInfo");
        msg.setInfo("2");
        msg.setOs(1);
        msg.setToken("3");
        return msg;
    }

    public static void rest1(){
        try {
            Socket socket = new Socket("10.1.3.32",8031);
            while (true){
                // 2、获取输出流，向服务器发送数据
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);


                pw.write("this is my username and password");
                pw.flush();
                //不必等待，立即发送
                socket.shutdownOutput();
                Thread.sleep(300000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
