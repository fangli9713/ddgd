package com.fangln.dd.init.socket;

import com.fangln.dd.util.CryptoUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.drafts.Draft_6455;

import java.net.URI;
import java.util.*;

/**
 * Created by Fangln on 2018/7/9.
 */
public class WebSocketClientTest {

    public static WebSocketClient client;

    public static int heartbeat = 0;// 0代表链接断开或者异常 1代表链接中.2代表正在连接
    public static String url="";//请求的路径地址包括端口

    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        Task task = new Task();
        timer.schedule(task, new Date(), 10000);
    }

    public static void connect() throws Exception {
        String parkId = "Q3bCydBWvT-kEcGhAJDtG3";
        String secret = "bcvbza2323zs22132123211a121";
        String p = CryptoUtil.MD5Encode(parkId+secret);
        String uri = "ws://10.1.3.129:8080/core/carpark/parkconn/Q3bCydBWvT-kEcGhAJDtG3?b="+p;

        client = new Client(new URI(uri), new Draft_6455(), null, 0);
        client.connect();
        int count = 0;
        heartbeat=2;
        while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
            count++;
            if (count % 1000000000 == 0) {
                System.out.println("还没有打开");
            }
        }

    }

    public static void reconnect() throws Exception {
        Thread.currentThread().sleep(15000);// 毫秒
        System.out.println("再次启动尝试连接");
        connect();
    }

    public static void send(byte[] bytes) {
        client.send(bytes);
    }
}

class Task extends TimerTask {

    @Override
    public void run() {
        try {
            System.out.println("心跳检测:"+((WebSocketClientTest.heartbeat == 1)?"连接中":"未连接中"));
            if (WebSocketClientTest.heartbeat ==0 ) {
                WebSocketClientTest.connect();
            }
            if(WebSocketClientTest.client!=null) {
                DataProtocol dataProtocol = new DataProtocol();
                dataProtocol.setCmd("abc");
                Map<String,String> map = new HashMap<>();
                map.put("1","2");
                dataProtocol.setData(map);
                dataProtocol.setMsgId("123");
                WebSocketClientTest.client.send(dataProtocol.toSendString());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
