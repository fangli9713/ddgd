package com.fangln.dd.init.netty;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fangln on 2018/7/25.
 */
public class ClientStart {

    public static void main(String[] args) throws InterruptedException {
        NettyClient bootstrap = new NettyClient(8087, "127.0.0.1");

        Map<String,String> dmap = new HashMap<>();
        dmap.put("token","123");
        while (true){
            Thread.sleep(5000);
            RequestInfo req = new RequestInfo();
            req.setType((byte) 1);
            req.setData(JSON.toJSONString(dmap));

            bootstrap.sendMessage(req);

        }
    }
}
