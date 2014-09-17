package com.subway.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.subway.action.DbBackAction;
import com.subway.action.RestoreDBAction;
import com.subway.dao.SyslogDAO;
import com.subway.domain.LogSearchVO;
import com.subway.domain.PageBean;
import com.subway.domain.SubwayUser;
import com.subway.domain.Syslog;
import com.subway.service.SysLogService;

@Component
public class SysLogServiceImpl implements SysLogService {
	private SyslogDAO dao = new SyslogDAO();

	@Override
	public String addSysLog(Syslog log) {
		dao.save(log);
		return "OK";
	}

	@Override
	public PageBean<Syslog> getList(PageBean<Syslog> pb) {
		return dao.findPage(pb);
	}

	@Override
	public List<Syslog> getAll() {
		return dao.findAll();
	}

	@Override
	public PageBean<Syslog> getLogListBySearchVO(LogSearchVO vo,
			PageBean<Syslog> pageBean) {
		// TODO Auto-generated method stub
		pageBean=dao.findByLimit(vo, pageBean);
		return pageBean;
	}
	public List<String> getAllInfo(String param){
		return dao.findAllInfo(param);
	}
	
	@Override
	public String backupLogTable() {
			int index=0;
			int sum=10;
			HSSFWorkbook wb = new HSSFWorkbook();
			List<String> title=new ArrayList<String>();
			
			title.add("id");
			title.add("name");
			title.add("logTime");
			title.add("opr");
			title.add("content");
			
			int[] recol=new int[5];
	    	for(int i=0;i<5;i++){
	    		recol[i]=i;
	    	}
			
			List<String[]> values=new ArrayList<String[]>();
	    	HSSFSheet sheet=DbBackAction.createMySheet(wb,"usersheet");
	    	DbBackAction.export(wb, sheet, "syslog", "syslog", title, recol, values);
	    	List<Syslog> listuser=dao.getSomeInfo1(index, sum);
	    	System.out.println("size:"+listuser.size());
	    		
	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	while (listuser.size()>0) {
	        	listuser=dao.getSomeInfo1(index, sum);
	        	values.clear();
	        	for (int i = 0; i < listuser.size(); i++) {
					String[] s=new String[6];
					s[0]=listuser.get(i).getId().toString();
					s[1]=listuser.get(i).getName();
					s[2]=df.format(listuser.get(i).getLogTime());
					s[3]=listuser.get(i).getOper();
					s[4]=listuser.get(i).getContent();
					values.add(s);
				}
	        	DbBackAction.writeInfo(wb,sheet,title,values,index);
	        	index=index+listuser.size();
	        	listuser.clear();
	        	listuser=dao.getSomeInfo1(index, sum);
	        	System.out.println("size:"+listuser.size());
	        	System.out.println("hello......");
	       	
			}
	    	DbBackAction.writeWorkbook(wb, "../webapps/log.xls");//  /ssh/dbBack/
	    	System.out.println("okokokoko");
			return "OK";
		}
	 
		@Override
		public String printLogTable(List<Syslog> listuser, String fileName) {
			System.out.println(fileName+"+++"+listuser);
			HSSFWorkbook wb = new HSSFWorkbook();
			List<String> title=new ArrayList<String>();
			
			title.add("id");
			title.add("name");
			title.add("logTime");
			title.add("opr");
			title.add("content");
			
			int[] recol=new int[5];
	    	for(int i=0;i<5;i++){
	    		recol[i]=i;
	    	}
			
			List<String[]> values=new ArrayList<String[]>();
	   	HSSFSheet sheet=DbBackAction.createMySheet(wb,"usersheet");
	    	DbBackAction.export(wb, sheet, "syslog", "syslog", title, recol, values);
	    	
	    	System.out.println("size:"+listuser.size());
	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	{
	        	values.clear();
	        	for (int i = 0; i < listuser.size(); i++) {
					String[] s=new String[6];
					s[0]=listuser.get(i).getId().toString();
					s[1]=listuser.get(i).getName();
					s[2]=df.format(listuser.get(i).getLogTime());
    				s[3]=listuser.get(i).getOper();
					s[4]=listuser.get(i).getContent();
					values.add(s);
				}
	        	DbBackAction.writeInfo(wb,sheet,title,values,0);
	        	listuser.clear();
	        	System.out.println("size:"+listuser.size());
	        	System.out.println("hello......");
	        	
			}
	    	DbBackAction.writeWorkbook(wb, fileName);
	    	System.out.println("okokokoko");
			return "OK";
		}
			
		private Syslog getSyslogFrom(List<String> list)
		{
			Syslog log=new Syslog();
			DateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try{
				log.setId(Integer.parseInt(list.get(0).trim()));
				log.setName(list.get(1).trim());
				log.setLogTime(dd.parse(list.get(2).trim()));
				log.setOper(list.get(3).trim());
				log.setContent(list.get(4).trim());
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
			return log;
		}
		
		@Override
		public String restoreLogTable() {
			dao.clearDBTable();
			List<String> list=null;
			try {  
				RestoreDBAction er = new RestoreDBAction("../webapps/log.xls");  
				list=er.readExcelFile();
				list=er.readExcelFile();
		    	list=er.readExcelFile();
		    	while(list!=null)
		    	{
		    		//er.printListContent(list);
		    		Syslog log=getSyslogFrom(list);
		    		if (log!=null) {
		    			dao.save(log);
					}
		    		list=er.readExcelFile();
		    	}
		        er.close();  
	        }catch(Exception e) {  
	            e.printStackTrace();  
	        }  
			System.out.println("syslog table restore ok!");
			return "OK";
		}
	
}
