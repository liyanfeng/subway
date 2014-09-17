package com.subway.domain;

import java.util.Date;

/**
 * AbstractFault entity provides the base persistence definition of the Fault
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractFault implements java.io.Serializable {

	// Fields

	private Integer id;// id
	private Date pdate;// 工单生成时间
	private String code="";// 故障编号
	private String grade="";// 故障等级
	private String major="";// 所属专业
	private String userowner="";// 所属工班
	private String finder="";// 报告人
	private String accepter="";// 接报人
	private Date acceptTime;// 接报时间
	private Date ptime;// 发生时间
	private Date backtime;// 恢复时间
	private String place="";// 故障地点
	private String present="";// 故障现象
	private String process="";// 处理经过
	private String reqModify="";// 修改请求
	private String reqback="";// 请求回执
	private String subwaystate="";// 故障状态
	private String cause="";// 故障原因
	private String isConfirm="";// 是否确认
	private String confirmPeople="";// 状态确认人
	private String generatePeople="";// 工单生成人
	private String device="";// 故障设备
	private String causeAnalyse="";// 故障原因分析
	private String analyseConfirmPeo="";// 原因分析确认
	private String causeConfirmPeo="";// 故障原因确认人

	// Constructors

	/** default constructor */
	public AbstractFault() {
	}

	/** full constructor */
	public AbstractFault(Integer id, Date pdate, String code, String grade,
			String major, String userowner, String finder, String accepter,
			Date acceptTime, Date ptime, Date backtime, String place,
			String present, String process, String reqModify, String reqback,
			String subwaystate, String cause, String isConfirm,
			String confirmPeople, String generatePeople, String device,
			String causeAnalyse, String analyseConfirmPeo,
			String causeConfirmPeo) {
		this.id = id;
		this.pdate = pdate;
		this.code = code;
		this.grade = grade;
		this.major = major;
		this.userowner = userowner;
		this.finder = finder;
		this.accepter = accepter;
		this.acceptTime = acceptTime;
		this.ptime = ptime;
		this.backtime = backtime;
		this.place = place;
		this.present = present;
		this.process = process;
		this.reqModify = reqModify;
		this.reqback = reqback;
		this.subwaystate = subwaystate;
		this.cause = cause;
		this.isConfirm = isConfirm;
		this.confirmPeople = confirmPeople;
		this.generatePeople = generatePeople;
		this.device = device;
		this.causeAnalyse = causeAnalyse;
		this.analyseConfirmPeo = analyseConfirmPeo;
		this.causeConfirmPeo = causeConfirmPeo;
	}

	
	
	// Property accessors

	@Override
	public String toString() {
		String temp= "{\"id\":\"" + id + "\", \"pdate\":\"" + pdate
				+ "\", \"code\":\"" + code + "\", \"grade\":\"" + grade
				+ "\", \"major\":\"" + major + "\", \"userowner\":\""
				+ userowner + "\", \"finder\":\"" + finder
				+ "\", \"accepter\":\"" + accepter + "\", \"acceptTime\":\""
				+ acceptTime + "\", \"ptime\":\"" + ptime
				+ "\", \"backtime\":\"" + backtime + "\", \"place\":\"" + place
				+ "\", \"present\":\"" + present + "\", \"process\":\""
				+ process + "\", \"reqModify\":\"" + reqModify
				+ "\", \"reqback\":\"" + reqback + "\", \"subwaystate\":\""
				+ subwaystate + "\", \"cause\":\"" + cause
				+ "\", \"isConfirm\":\"" + isConfirm
				+ "\", \"confirmPeople\":\"" + confirmPeople
				+ "\", \"generatePeople\":\"" + generatePeople
				+ "\", \"device\":\"" + device + "\", \"causeAnalyse\":\""
				+ causeAnalyse + "\", \"analyseConfirmPeo\":\""
				+ analyseConfirmPeo + "\", \"causeConfirmPeo\":\""
				+ causeConfirmPeo + "\"}";
				String result=temp.replaceAll("null","").replace("\n","  ");
				
				return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getUserowner() {
		return userowner;
	}

	public void setUserowner(String userowner) {
		this.userowner = userowner;
	}

	public String getFinder() {
		return finder;
	}

	public void setFinder(String finder) {
		this.finder = finder;
	}

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	public Date getBacktime() {
		return backtime;
	}

	public void setBacktime(Date backtime) {
		this.backtime = backtime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getReqModify() {
		return reqModify;
	}

	public void setReqModify(String reqModify) {
		this.reqModify = reqModify;
	}

	public String getReqback() {
		return reqback;
	}

	public void setReqback(String reqback) {
		this.reqback = reqback;
	}

	public String getSubwaystate() {
		return subwaystate;
	}

	public void setSubwaystate(String subwaystate) {
		this.subwaystate = subwaystate;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getConfirmPeople() {
		return confirmPeople;
	}

	public void setConfirmPeople(String confirmPeople) {
		this.confirmPeople = confirmPeople;
	}

	public String getGeneratePeople() {
		return generatePeople;
	}

	public void setGeneratePeople(String generatePeople) {
		this.generatePeople = generatePeople;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getCauseAnalyse() {
		return causeAnalyse;
	}

	public void setCauseAnalyse(String causeAnalyse) {
		this.causeAnalyse = causeAnalyse;
	}

	public String getAnalyseConfirmPeo() {
		return analyseConfirmPeo;
	}

	public void setAnalyseConfirmPeo(String analyseConfirmPeo) {
		this.analyseConfirmPeo = analyseConfirmPeo;
	}

	public String getCauseConfirmPeo() {
		return causeConfirmPeo;
	}

	public void setCauseConfirmPeo(String causeConfirmPeo) {
		this.causeConfirmPeo = causeConfirmPeo;
	}

}