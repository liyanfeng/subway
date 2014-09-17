package com.subway.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.subway.domain.LogSearchVO;
import com.subway.domain.PageBean;
import com.subway.domain.Syslog;
import com.subway.service.SysLogService;

public class LogAction extends BaseAction<Syslog>{
	private LogSearchVO logvo;//这里有log的四个属性 还有重写的logvo的tostring方法
	@Autowired
	private SysLogService ss;//
	private Syslog log;
	private String begintime;
	private String endtime;
	private String oper;
	private String name;
	private Integer pageIndex;
	private Integer pageSize;
	public String findLogBySearchVO(){
		pageIndex=Integer.parseInt(getRequest().getParameter("pageIndex"));
		pageSize=Integer.parseInt(getRequest().getParameter("pageSize"));
		writeHtml(getJsonFromString(find()));
		return NONE;
	}
	public PageBean<Syslog> find(){
		logvo = new LogSearchVO();
		logvo.setBegintime(begintime);
		logvo.setEndtime(endtime);
		logvo.setName(name);
		logvo.setOper(oper);
		System.out.println(logvo.getOper());
		
		PageBean<Syslog> pb = new PageBean<Syslog>();
		pb.setPageIndex(pageIndex+1);
		pb.setPageSize(pageSize);
		ss.getLogListBySearchVO(logvo, pb);
		return pb;	
	}	
	public String exportdata() throws IOException{
		pageIndex=0;
		pageSize=999999999;
		String basePath = getRealPath();
		basePath=basePath.replace("\\", "/");
		System.out.println("base:"+basePath);
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		PageBean<Syslog> pb = find();
		ss.printLogTable(pb.getDatas(), basePath+"dbBack/"+sdf.format(d)+".xls");
		writeHtml(sdf.format(d)+".xls");
		return  NONE;
	}
	public String getAllOper(){
		
		String temp = getJsonArryFromString("oper",ss.getAllInfo("oper"));
		System.out.println(temp);
		writeJson(temp);
		return NONE;
	}

	
	
	
	
	
	
	
	
	
	
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public LogSearchVO getLogvo() {
		return logvo;
	}
	public void setLogvo(LogSearchVO logvo) {
		this.logvo = logvo;
	}
	public SysLogService getLogservice() {
		return ss;
	}
	public void setLogservice(SysLogService logservice) {
		this.ss = logservice;
	}
	public Syslog getLog() {
		return log;
	}
	public void setLog(Syslog log) {
		this.log = log;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
	
}
