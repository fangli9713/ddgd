package com.fangln.dd.init.netty;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {
    private int loss_connect_time = 0;

    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled
            .unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",
                    CharsetUtil.UTF_8));

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            System.out.println("event.state==="+event.state());
            if (event.state() == IdleState.READER_IDLE) {
                Map<String,Object> map = new HashMap<>();
                map.put("1",2);
              //  SocketHandler.writeChannel(ctx,0, JSON.toJSONString(map),"heart");
               // loss_connect_time++;
                if (loss_connect_time > 2) {
                   // ctx.channel().close();
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
    
}
