package com.subway.domain;

/**
 * SubwayUser entity. @author MyEclipse Persistence Tools
 */
public class SubwayUser extends AbstractSubwayUser implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** default constructor */
	public SubwayUser() {
	}

	/** full constructor */
	public SubwayUser(String name, String username,String pwd, String major, String userowner,
			Integer authen) {
		super(name,username, pwd, major, userowner, authen);
	}

}
