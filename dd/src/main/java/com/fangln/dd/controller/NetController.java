package com.fangln.dd.controller;

import com.fangln.dd.init.netty.client.NettyClient;
import com.fangln.dd.init.netty.dto.BaseMsgOuterClass;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Fangln on 2019/1/30.
 */
@Controller
public class NetController {

    @RequestMapping("/link")
    public void link(HttpServletRequest request, HttpServletResponse response){

        NettyClient nettyClient = new NettyClient();
        nettyClient.connect();
        final Channel channelFuture = nettyClient.cf.channel();
        System.out.println("channelFuture==="+channelFuture);
        BaseMsgOuterClass.BaseMsg.Builder msg  = BaseMsgOuterClass.BaseMsg.newBuilder();
        msg.setToken("12345678");
        msg.setOs(1);
        msg.setInfo("111");
        msg.setMethod("2");

        channelFuture.writeAndFlush("222222222222");
    }
}
