package com.fangln.dd.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogUtil {

	private static Log log=LogFactory.getLog("parkplus.framework");
    
	public static Log getLog() {
		return log;
	}

	public static void setLog(Log log1) {
		log = log1;
	}
}
