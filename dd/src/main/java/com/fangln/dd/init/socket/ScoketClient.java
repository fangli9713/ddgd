package com.fangln.dd.init.socket;

import com.alibaba.fastjson.JSON;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.*;

public class ScoketClient {
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		
		TimerTask timerTask = new TimerTask() {
			Socket socket = null;
			BufferedWriter writer = null;
			OutputStreamWriter ow = null;
			OutputStream os = null;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
					try {
						if(socket == null || socket.isClosed()){
							socket = new Socket("localhost",8087);
						}
						OutputStream ots = socket.getOutputStream();
						ow = new OutputStreamWriter(os);
						writer = new BufferedWriter(ow);
						Map<String,String> map = new HashMap<>();
						map.put("cmd", "123");
						writer.write(JSON.toJSONString(map));
						writer.flush();
//						socket.shutdownOutput();
//						System.out.println("消息发送完成");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		};

		timer.schedule(timerTask, new Date(),10000);

	}

}
