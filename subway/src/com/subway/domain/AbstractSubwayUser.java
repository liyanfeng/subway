package com.subway.domain;

/**
 * AbstractSubwayUser entity provides the base persistence definition of the
 * SubwayUser entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSubwayUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String username;
	private String pwd;
	private String major;
	private String userowner;
	private Integer authen;

	

	/** default constructor */
	public AbstractSubwayUser() {
	}

	/** full constructor */
	public AbstractSubwayUser(String name,String username, String pwd, String major,
			String userowner, Integer authen) {
		this.name = name;
		this.username=username;
		this.pwd = pwd;
		this.major = major;
		this.userowner = userowner;
		this.authen = authen;
	}

	// Property accessors

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getUserowner() {
		return this.userowner;
	}

	public void setUserowner(String userowner) {
		this.userowner = userowner;
	}

	public Integer getAuthen() {
		return this.authen;
	}

	public void setAuthen(Integer authen) {
		this.authen = authen;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"name\":\"" + name + 
				"\",\"username\":\"" + username +"\",\"pwd\":\"" + pwd +
				"\",\"major\":\""
				+ major + "\",\"userowner\":\"" + userowner + "\",\"authen\":\"" + authen + "\"}";
	}


	 
	
}