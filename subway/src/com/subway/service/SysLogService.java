package com.subway.service;

import java.util.List;

import com.subway.domain.LogSearchVO;
import com.subway.domain.PageBean;
import com.subway.domain.SubwayUser;
import com.subway.domain.Syslog;
import com.subway.domain.UserSearchVO;

public interface SysLogService {
	
	public String addSysLog(Syslog log);
	public PageBean<Syslog> getList(PageBean<Syslog> pb);
	public List<Syslog> getAll();
	public PageBean<Syslog> getLogListBySearchVO(LogSearchVO vo ,PageBean<Syslog> pageBean);
	public List<String> getAllInfo(String param);
	public String printLogTable(List<Syslog> listuser, String fileName);
	public String backupLogTable();
	public String restoreLogTable();
	
}
