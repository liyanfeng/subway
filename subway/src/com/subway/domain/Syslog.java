package com.subway.domain;

import java.util.Date;

/**
 * Syslog entity. @author MyEclipse Persistence Tools
 */
public class Syslog extends AbstractSyslog implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Syslog() {
	}

	/** full constructor */
	public Syslog(String name, Date logTime, String oper, String content) {
		super(name, logTime, oper, content);
	}

}
