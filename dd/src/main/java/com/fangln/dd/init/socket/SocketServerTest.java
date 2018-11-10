package com.fangln.dd.init.socket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint("/websocket/{myWebsocket}")  
public class SocketServerTest {
	
	private static final Logger logger = Logger.getLogger(SocketServerTest.class);  
	  
    public static Map<String, Session> clients = new ConcurrentHashMap<String, Session>();  
  
    /** 
     * 打开连接时触发 
     * @param myWebsocket 
     * @param session 
     */  
    @OnOpen  
    public void onOpen(@PathParam("myWebsocket") String myWebsocket, Session session){  
        System.out.println("Websocket Start Connecting:" + myWebsocket);  
        System.out.println("进入："+myWebsocket);  
        clients.put(myWebsocket, session);  
    }  
  
    /** 
     * 收到客户端消息时触发 
     * @param myWebsocket 
     * @param message 
     * @return 
     */  
    @OnMessage  
    public String onMessage(@PathParam("myWebsocket") String myWebsocket, String message) {
        System.out.println("onMessage");
        return "Got your message ("+ message +").Thanks !";  
    }  
  
    /** 
     * 异常时触发 
     * @param myWebsocket 
     * @param throwable 
     */  
    @OnError  
    public void onError(@PathParam("myWebsocket") String myWebsocket, Throwable throwable) {  
        System.out.println("Websocket Connection Exception:" + myWebsocket);  
        clients.remove(myWebsocket);
    }  
  
    /** 
     * 关闭连接时触发 
     * @param myWebsocket 
     */  
    @OnClose  
    public void onClose(@PathParam("myWebsocket") String myWebsocket) {  
        System.out.println("Websocket Close Connection:" + myWebsocket);  
        clients.remove(myWebsocket);  
    }  
  
  
    /** 
     * 将数据传回客户端 
     * 异步的方式 
     * @param myWebsocket 
     * @param message 
     */  
    public static void broadcast(String myWebsocket, String message) {  
        if (clients.containsKey(myWebsocket)) {  
            clients.get(myWebsocket).getAsyncRemote().sendText(message);  
        } else {  
            throw new NullPointerException("[" + myWebsocket +"]Connection does not exist");  
        }  
    }  
    
	public static void main(String[] args) {
		
		
		
	}

}
