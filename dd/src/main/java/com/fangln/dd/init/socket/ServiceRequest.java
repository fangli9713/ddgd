package com.fangln.dd.init.socket;

import java.util.Map;

public interface ServiceRequest extends DataFrame{

	public Map<String,Object> getContext();
	
}
