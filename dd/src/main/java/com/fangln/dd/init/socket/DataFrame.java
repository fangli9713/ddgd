package com.fangln.dd.init.socket;

public interface DataFrame {

	public String getCmd();

	public String getMsgId();

	public String getTimeMills();

	public <T> T getData();

	public void setCmd(String cmd);

	public void setMsgId(String msgId);

	public void setTimeMills(String timeMills);

	public void setData(Object data);
	
	
}
