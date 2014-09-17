package com.subway.service;

import java.util.List;

import com.subway.domain.PageBean;
import com.subway.domain.SubwayUser;
import com.subway.domain.UserSearchVO;

public interface UserService {
	
	public PageBean<SubwayUser> getList(PageBean<SubwayUser> pb);
	public String addUser(SubwayUser user);
	public String delUser(SubwayUser user);
	public String updateUserAuthen(SubwayUser user);
	public SubwayUser getUserById(int id);
	public SubwayUser getUserByName(String name);
	public PageBean<SubwayUser> getUserListBySearchVO(UserSearchVO vo ,PageBean<SubwayUser> pageBean);
	public List<String> getAllInfo(String param);
	public String backupUserTable();
	public String restoreUserTable();
}
