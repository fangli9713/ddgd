package com.fangln.dd.init.socket;


import com.fangln.dd.util.CryptoUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import javax.websocket.*;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

@ClientEndpoint
public class SocketClientTest {
	public static WebSocketClient client;
	public static void main(String[] args) {
		try {
//			client = new WebSocketClient(new URI("ws://test0.beckparking.com:80/core/carpark/parkconn/123456"),new Draft_6455()) {

			String parkId = "Q3bCydBWvT-kEcGhAJDtG3";
			String secret = "bcvbza2323zs22132123211a121";
			String p = CryptoUtil.MD5Encode(parkId+secret);
			String uri = "ws://10.1.3.129:8080/core/carpark/parkconn/Q3bCydBWvT-kEcGhAJDtG3?b="+p;
			client = new WebSocketClient(new URI(uri),new Draft_6455()) {
				@Override
				public void onOpen(ServerHandshake serverHandshake) {
					System.out.println("握手成功");
				}

				@Override
				public void onMessage(String msg) {
					System.out.println("收到消息=========="+msg);
					if(msg.equals("over")){
						client.close();
					}

				}

				@Override
				public void onClose(int i, String s, boolean b) {
					System.out.println("链接已关闭");
				}

				@Override
				public void onError(Exception e){
					e.printStackTrace();
					System.out.println("发生错误已关闭");
				}
			};
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			client.connect();
			System.out.println("client.getDraft="+client.getDraft());
			int i = 1;
			//连接成功,发送信息
			while(!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)){
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("正在连接..."+i++);
			}
			client.send("哈喽,连接一下啊");
			System.out.println("连接成功");
			client.onMessage("");
			final Socket socket = client.getSocket();
			final OutputStream outputStream = socket.getOutputStream();
		}catch (Exception e){
			e.printStackTrace();
		}



	}
}
