package com.subway.action;

import hn.cartel.common.utils.MyMD5Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.jboss.weld.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.subway.domain.PageBean;
import com.subway.domain.SubwayUser;
import com.subway.domain.UserSearchVO;
import com.subway.service.FaultService;
import com.subway.service.SysLogService;
import com.subway.service.UserService;

public class UserAction extends BaseAction<SubwayUser>{
	
	private static final long serialVersionUID = 1L;
	private SubwayUser user ;
	@Autowired
	private UserService us;
	
	@Autowired
	private SysLogService ss;
	
	@Autowired
	private FaultService fs;
	
	private UserSearchVO  uservo;
	private Integer pageIndex;
	private Integer pageSize;
	private String name;
	private String major;
	private String userowner;
	private String data;
	private String username;
	private String pwd;
	private int id;
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	

	public String addNewUser()
	{
		System.out.println("data:"+data);
		SubwayUser user = JSON.parseObject(data, SubwayUser.class);
//		try {
//			user.setPwd(MyMD5Util.getEncryptedPwd(user.getPwd()));//md5加密 。。
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		writeHtml(us.addUser(user));
		return NONE;
	}
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
	public String getUserowner() {
		return userowner;
	}
	public void setUserowner(String userowner) {
		this.userowner = userowner;
	}
	/**
	 * 用户登录
	 * @return	
	 * @throws IOException
	 */
	public String login() throws IOException, NoSuchAlgorithmException{
		int flag=-1;
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session =request.getSession();
		
		SubwayUser subwayuser = null;
		if(this.getUsername()!=null&&this.getPwd()!=null){
			request.setAttribute("username", this.getUsername());
			subwayuser =us.getUserByName(this.getUsername());
			if(subwayuser !=null){
				if (this.getPwd().equals(subwayuser.getPwd())) {
					flag=subwayuser.getAuthen();
					session.setAttribute("username",subwayuser.getUsername());
					session.setAttribute("major",subwayuser.getMajor());
					session.setAttribute("name",subwayuser.getName());
					session.setAttribute("authen",subwayuser.getAuthen());
					session.setAttribute("userowner", subwayuser.getUserowner());
//					addCount();
					
				
				
				}
			}
		}
		if(flag==6){
			return ""+flag;
		}else if (flag>1) {
			return "1";
//			getResponse().sendRedirect("/index.jsp");
//			getSession().put("currentUser",subwayuser);
		}else{
			return "error";
//			writeHtml("用户名或密码错误");
		}
//		return NONE;
	}
	
	public String addCount(){
		ServletContext sct = ServletActionContext.getServletContext();
		Object temp=sct.getAttribute("count");
		if(temp==null){
			sct.setAttribute("count",1);
			writeHtml("1");
		}else{
			int tempint = Integer.parseInt(temp.toString())+1;
			sct.setAttribute("count", tempint);
			writeHtml(""+tempint);
		}
		
		return NONE;
	}
	public String subCount(){
		ServletContext sct = ServletActionContext.getServletContext();
		Object temp=sct.getAttribute("count");
		int tempint = Integer.parseInt(temp.toString())-1;
		sct.setAttribute("count", tempint);
		writeHtml(""+tempint);
		return NONE;
		
	}
	
	/**
	 * 获取所有专业，以json返回
	 * @return
	 */
	public String getAllMajor(){
		String tmp=getJsonArryFromString("major",us.getAllInfo("major"));
		System.out.println(tmp);
		writeJson(tmp);
		return NONE;
	}
	
	public String getAllUserOwner(){
		String tmp=getJsonArryFromString("userowner",us.getAllInfo("userowner"));
		System.out.println(tmp);
		writeJson(tmp);
		return NONE;
	}
	
	
	public String logout() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session =request.getSession();
		session.invalidate();
		getResponse().sendRedirect("/ssh/ldenglu.html");
		return NONE;
	}
	
	/**
	 *	 findUserBySearchVO
	 * @return
	 */
	
	public String findUserBySearchVO(){
		uservo = new UserSearchVO();
		System.out.println(uservo==null);
		System.out.println(major+"\n"+name+"\n"+userowner);
		uservo.setMajor(major);
		uservo.setName(name);
		uservo.setUserowner(userowner);
		System.out.println(major+"====================="+getRequest().getAttribute("major"));

		
		System.out.println("1111");
		System.out.println(getRequest()==null);
		pageIndex=Integer.parseInt(getRequest().getParameter("pageIndex"));
		pageSize=Integer.parseInt(getRequest().getParameter("pageSize"));
		PageBean<SubwayUser> pb = new PageBean<SubwayUser>();
		System.out.println("1111");
		pb.setPageIndex(pageIndex+1);
		pb.setPageSize(pageSize);
		System.out.println(uservo==null);
		System.out.println(pb==null);
		us.getUserListBySearchVO(uservo,pb);
		writeJson(getJsonFromString(pb));
		return NONE;
	}

	public String getUserInfo()
	{
		SubwayUser user=us.getUserById(id);
		writeJson(user.toString());
		return NONE;
	}
	
	public String delUserInfo()
	{
		writeHtml(us.delUser(us.getUserById(id)));
		return NONE;
	}
	
	public String modifyUser()
	{
		SubwayUser user = JSON.parseObject(data, SubwayUser.class);
//		SubwayUser user1 = us.getUserById(user.getId());
//		user.setUsername(user1.getUsername());
//		user.setPwd(user1.getPwd());
		writeHtml(us.updateUserAuthen(user));
		return NONE;
	}

	public SubwayUser getUser() {
		return user;
	}


	public void setUser(SubwayUser user) {
		this.user = user;
	}


	public UserSearchVO getUservo() {
		return uservo;
	}


	public void setUservo(UserSearchVO uservo) {
		this.uservo = uservo;
	}
		
	public void checkUsername(){
		if(us.getUserByName(username)==null){
			System.out.println("success");
			writeHtml("success");
		}else{
			System.out.println("error");
			writeHtml("error");
		}
		
	}
	
	public String backUserTable()
	{	
		String re1=us.backupUserTable();
		String re2=ss.backupLogTable();
		String re3=fs.backupFaultTable();
		if (re1.equals("OK")&&re2.equals("OK")&&re3.equals("OK")) {
			writeHtml("OK");
		}
		else
		{
			writeHtml("error");
		}
		return NONE;
	}
	
	public String restoreUserTable()
	{
		String re1=us.restoreUserTable();
		String re2=ss.restoreLogTable();
		String re3=fs.restoreFaultTable();
		if (re1.equals("OK")&&re2.equals("OK")&&re3.equals("OK")) {
			writeHtml("OK");
		}
		else
		{
			writeHtml("error");
		}
		return NONE;
	}

}
