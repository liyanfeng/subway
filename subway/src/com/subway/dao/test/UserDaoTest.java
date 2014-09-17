package com.subway.dao.test;

import com.subway.dao.SubwayUserDAO;
import com.subway.domain.PageBean;
import com.subway.domain.SubwayUser;
import com.subway.domain.UserSearchVO;

public class UserDaoTest {
	  public static void main(String[] args) {
		SubwayUserDAO sud = new SubwayUserDAO();
		UserSearchVO vo = new UserSearchVO();
		vo.setName("donar");
		PageBean<SubwayUser> pageBean = new PageBean<SubwayUser>();
		pageBean.setPageIndex(1);
		pageBean.setPageSize(10);
		sud.findByLimit(vo, pageBean);
		System.out.println(pageBean.getDatas());
	}
}	
