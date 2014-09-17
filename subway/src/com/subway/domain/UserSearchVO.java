package com.subway.domain;

public class UserSearchVO {
	private String name;
	private String major;
	private Integer authen;
	private String userowner;	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Integer getAuthen() {
		return authen;
	}
	public void setAuthen(Integer authen) {
		this.authen = authen;
	}
	public String getUserowner() {
		return userowner;
	}
	public void setUserowner(String userowner) {
		this.userowner = userowner;
	}
	@Override
	public String toString() {
		return "{\"${member.name()\":\"" + name + "\",\"" + major + "\",\""
				+ authen + "\",\"" + userowner + "\"}}";
	}
	
	
	
}
