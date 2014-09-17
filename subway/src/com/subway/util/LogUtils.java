package com.subway.util;

import java.util.Date;
import java.util.Map;

import com.subway.domain.Syslog;
import com.subway.service.SysLogService;
import com.subway.service.impl.SysLogServiceImpl;

public class LogUtils {
	public static void log(Map<String, Object> session,String something,String operation){
		SysLogService ls = new SysLogServiceImpl();
		Syslog log= new Syslog();
		log.setName(session.get("username").toString());
		log.setOper(operation);
		log.setContent(something);
		log.setLogTime(new Date());
		ls.addSysLog(log);
	}
}
