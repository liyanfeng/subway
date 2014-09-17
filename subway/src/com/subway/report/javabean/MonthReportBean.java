package com.subway.report.javabean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.subway.dao.FaultDAO;

public class MonthReportBean extends FaultDAO{
	public Date date;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/****z这个事得到本月和上月某种类型的故障数目 num=1是上个月 的
	 *  仅仅局限于 iscs公班的 back=1 表示查找恢复的
	 *  1表示修改
	 * @param i
	 * @param num
	 * @return
	 */
	public String getdate(int num){
		date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(num==1){
			calendar.add(calendar.MONTH, -1);	
		}
		String searchdate=sdf.format(calendar.getTime());
		System.out.println(searchdate);
		return searchdate;
	}
	public int getuserowner(String userowner,String cause,int num,int back){
		String searchdate=getdate(num);
		StringBuffer sql=new StringBuffer("select count(*) from fault where cause='"+cause+"' and userowner='"+userowner+"' and datediff(month,acceptTime,'"+searchdate+"')=0 ");
		if(back==1){
			sql.append(" and backtime is not null ");
		}
		Session session = getSession();
		Query q =session.createSQLQuery(sql.toString());
		List list=q.list();
		if(list==null){
			return 0;
		}else{
			return Integer.parseInt(list.get(0).toString()) ;
		}
	}
	/**
	 * 得到每个major各个处理的事件
	 * @param major
	 * @param num
	 * @param back
	 * @return
	 */
	public int geteverymajor(String major,int num,int back){
		String searchdate=getdate(num);
		StringBuffer sql=new StringBuffer("select count(*) from fault where major='"+major+"' and datediff(month,acceptTime,'"+searchdate+"')=0 ");
		if(back==1){
			sql.append(" and backtime is not null ");
		}
		Session session = getSession();
		Query q =session.createSQLQuery(sql.toString());
		List list=q.list();
		if(list==null){
			return 0;
		}else{
			return Integer.parseInt(list.get(0).toString()) ;
		}
	}
	/**
	 *dedao meige major chuli de gege shiqing de shumu 
	 */
	public int geteverymajoruserowner(String major,String userowner,int num,int back){
		String searchdate=getdate(num);
		StringBuffer sql=new StringBuffer("select count(*) from fault where major='"+major+"' and userowner='"+userowner+"' and datediff(month,acceptTime,'"+searchdate+"')=0 ");
		if(back==1){
			sql.append(" and backtime is not null ");
		}
		Session session = getSession();
		Query q =session.createSQLQuery(sql.toString());
		List list=q.list();
		if(list==null){
			return 0;
		}else{
			return Integer.parseInt(list.get(0).toString()) ;
		}
		
	}
		
}
