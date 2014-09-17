package com.subway.domain;

import java.util.Date;

/**
 * AbstractSyslog entity provides the base persistence definition of the Syslog
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSyslog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Date logTime;
	private String oper;
	private String content;

	// Constructors

	/** default constructor */
	public AbstractSyslog() {
	}

	/** full constructor */
	public AbstractSyslog(String name, Date logTime, String oper, String content) {
		this.name = name;
		this.logTime = logTime;
		this.oper = oper;
		this.content = content;
	}
	public String toString() {
		return "{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"logTime\":\""
				+ logTime + "\",\"oper\":\"" + oper + "\",\"content\":\"" + content + "\"}";
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getOper() {
		return this.oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}