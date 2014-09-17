package com.subway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.subway.action.DbBackAction;
import com.subway.action.RestoreDBAction;
import com.subway.dao.SubwayUserDAO;
import com.subway.domain.PageBean;
import com.subway.domain.SubwayUser;
import com.subway.domain.UserSearchVO;
import com.subway.service.UserService;
@Component
public class UserServiceImpl implements UserService {
	private SubwayUserDAO dao=new SubwayUserDAO(); 
	@Override
	public PageBean<SubwayUser> getList(PageBean<SubwayUser> pb) {
		
		return dao.findPage(pb);
	}

	@Override
	public String addUser(SubwayUser user) {
		dao.save(user);
		return "OK";
	}

	@Override
	public String delUser(SubwayUser user) {
		dao.delete(user);
		return "OK";
	}

	@Override
	public String updateUserAuthen(SubwayUser user) {
		dao.attachDirty(user);
		return "OK";
	}

	@Override
	public SubwayUser getUserById(int id) {
		
		return dao.findById(id);
	}

	@Override
	public SubwayUser getUserByName(String username) {
		
		return dao.findByUserName(username);
	}
	
	
	public PageBean<SubwayUser> getUserListBySearchVO(UserSearchVO vo ,PageBean<SubwayUser> pageBean){
		 pageBean=dao.findByLimit(vo,pageBean);
		return pageBean;
	}

	@Override
	public List<String> getAllInfo(String param) {
		return dao.findAllInfo(param);
	}

	@Override
	public String backupUserTable() {
		int index=0;
		int sum=10;
		HSSFWorkbook wb = new HSSFWorkbook();
		List<String> title=new ArrayList<String>();
		title.add("id");
		title.add("name");
		title.add("pwd");
		title.add("major");
		title.add("userowner");
		title.add("authen");
		title.add("username");
		
		int[] recol=new int[title.size()];
    	for(int i=0;i<title.size();i++){
    		recol[i]=i;
    	}
		
		List<String[]> values=new ArrayList<String[]>();
    	HSSFSheet sheet=DbBackAction.createMySheet(wb,"usersheet");
    	DbBackAction.export(wb, sheet, "user", "subway_user", title, recol, values);
    	List<SubwayUser> listuser=dao.getSomeInfo(index, sum);
    	System.out.println("size:"+listuser.size());
    	while (listuser.size()>0) {
        	listuser=dao.getSomeInfo(index, sum);
        	values.clear();
        	for (int i = 0; i < listuser.size(); i++) {
				String[] s=new String[title.size()];
				s[0]=listuser.get(i).getId().toString();
				s[1]=listuser.get(i).getName();
				s[2]=listuser.get(i).getPwd();
				s[3]=listuser.get(i).getMajor();
				s[4]=listuser.get(i).getUserowner();
				s[5]=listuser.get(i).getAuthen().toString();
				s[6]=listuser.get(i).getUsername();
				values.add(s);
			}
        	DbBackAction.writeInfo(wb,sheet,title,values,index);
        	index=index+listuser.size();
        	listuser.clear();
        	listuser=dao.getSomeInfo(index, sum);
        	System.out.println("size:"+listuser.size());
        	System.out.println("hello......");
        	
		}
    	DbBackAction.writeWorkbook(wb, "../webapps/user.xls");//  /ssh/dbBack/
    	System.out.println("okokokoko");
		return "OK";
	}

	private SubwayUser getUserFrom(List<String> list)
	{
		SubwayUser user=new SubwayUser();
		try{
			user.setId(Integer.parseInt(list.get(0).trim()));
			user.setName(list.get(1).trim());
			user.setPwd(list.get(2).trim());
			user.setMajor(list.get(3).trim());
			user.setUserowner(list.get(4).trim());
			user.setAuthen(Integer.parseInt(list.get(5).trim()));
			user.setUsername(list.get(6));
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	@Override
	public String restoreUserTable() {
		dao.clearDBTable();
		List<String> list=null;
		try {  
			RestoreDBAction er = new RestoreDBAction("../webapps/user.xls");  
			list=er.readExcelFile();
			list=er.readExcelFile();
	    	list=er.readExcelFile();
	    	while(list!=null)
	    	{
	    		//er.printListContent(list);
	    		SubwayUser user=getUserFrom(list);
	    		if (user!=null) {
	    			dao.save(user);
				}
	    		list=er.readExcelFile();
	    	}
	        er.close();  
        }catch(Exception e) {  
            e.printStackTrace();  
        }  
		System.out.println("user table restore ok!");
		return "OK";
	}


}
