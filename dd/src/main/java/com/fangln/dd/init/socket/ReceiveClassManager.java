package com.fangln.dd.init.socket;

import java.util.HashMap;
import java.util.Map;

public class ReceiveClassManager {

	private static final ReceiveClassManager instance=new ReceiveClassManager();
	private Map<String,Class<?>> dataClassMap=new HashMap<String,Class<?>>();
	
	public static ReceiveClassManager getInstance(){
		return instance;
	}
	
	private ReceiveClassManager(){
		
	}
	
	public void addDataClass(String cmd,Class<?> cl){
		dataClassMap.put(cmd, cl);
	}
	
	public Class<?> getDataClass(String cmd){
		return dataClassMap.get(cmd);
	}
	
}
