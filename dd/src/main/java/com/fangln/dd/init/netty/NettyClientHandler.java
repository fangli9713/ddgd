package com.fangln.dd.init.netty;

import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fangln on 2018/7/25.
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<RequestInfo> {


    protected void messageReceived(ChannelHandlerContext ctx, RequestInfo msg) throws Exception {
        System.out.println(msg.getData());
        RequestInfo req = new RequestInfo();
        req.setType(msg.getType());
        Map<String,String> dmap = new HashMap<>();
        dmap.put("status","ok");
        req.setData(new Gson().toJson(dmap));
        ctx.channel().writeAndFlush(req);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestInfo requestInfo) throws Exception {

    }
}
