package com.fangln.dd.init.socket;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataProtocol{

	private String cmd;
	private String msgId;
	private String timeMills;
	private Object data;
	
	private String recvMsg;
	
	private Map<String, Object> contextMap=null;
	
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd=cmd;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId=msgId;
	}

	public String getTimeMills() {
		return timeMills;
	}

	public void setTimeMills(String timeMills) {
		this.timeMills=timeMills;
	}

	@SuppressWarnings("unchecked")
	public <T> T getData() {
		return  (T)data;
	}

	public void setData(Object data) {
		this.data=data;
	}

	public String toSendString() {
	    String dataValue;
	    if(data!=null){
	    	dataValue=JSON.toJSONString(data);
	    }else{
	    	dataValue=null;	
	    }
	    Object l=data;
	    data=dataValue;
	    String result;
	    try{
	    	result=JSON.toJSONString(this);
	    }finally{
	    	data=l;
	    }
	    
	    return result;
	}


	public static DataProtocol parse(String jsonString) throws Exception {

		DataProtocol p=(DataProtocol)JSON.parse(jsonString);
		
		if(p.cmd==null){
			throw new Exception("缺少参数 cmd! 数据:"+jsonString);
		}
		
		if(p.getMsgId()==null){
			throw new Exception("缺少参数 msgId! 数据:"+jsonString);
		}
		
		if(p.data!=null){
			 Class<?> cl=ReceiveClassManager.getInstance().getDataClass(p.cmd);
             if(cl==null){
				 cl=LinkedHashMap.class;
			 }
             Object d= JSON.parse((String)p.getData());
             p.setData(d);
		}
		return p;
	}

	public String getRecvMsg() {
		return recvMsg;
	}

	public void setRecvMsg(String recvMsg) {
		this.recvMsg = recvMsg;
	}

	
}
