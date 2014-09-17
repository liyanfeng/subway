package com.subway.report.javabean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.subway.dao.FaultDAO;
import com.subway.domain.Fault;

public class DayReportBean extends FaultDAO{
	//统计指定日期指定部门已恢复的故障数目
	public int solvedFault4Day(String major,Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
		String code="TH"+sdf.format(date);
		String hql="from Fault where major='"+major+"' and subwaystate='已恢复' and code like '"+code+"%'";
		Query query=getSession().createQuery(hql);
		return query.list().size();
	}
	final String diaodu="已处理";
	final String gongban="未处理' or isConfirm='已确定 ";
	final String gongchengshi="已分析";
	public int getUnprocessSum(String userowner,String authen)
	{
		String hql="from Fault where 1=1";
		if (authen.equals("4")) {
			hql+=" and userowner='"+userowner+"' and isConfirm='"+gongchengshi+"'";
		}
		else if(authen.equals("3")||authen.equals("5"))
		{
			hql+=" and isConfirm='"+diaodu+"'";
		}
		else if(authen.equals("2"))
		{
			hql+=" and userowner='"+userowner+"' and isConfirm='"+gongban+"'";
		}
		Query query=getSession().createQuery(hql);
		return query.list().size();
	}
	//统计指定日期指定部门未恢复的故障数目
	public int unSolvedFault4Day(String major,Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
		String code="TH"+sdf.format(date);
		String hql="from Fault where major='"+major+"' and subwaystate='未恢复' and code like '"+code+"%'";
		Query query=getSession().createQuery(hql);
		return query.list().size();
	}
	
	@SuppressWarnings("unchecked")
	public List<Fault> getFaultToday()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
		Date date=new Date();
		String code="TH"+sdf.format(date);
		String hql="from Fault where  code like '"+code+"%'";
		Query query=getSession().createQuery(hql);
		return query.list();
	}
	
	//统计指定月份指定部门已恢复的故障数目
		public int solvedFault4Month(String major,Date date){
			SimpleDateFormat sdf=new SimpleDateFormat("yyMM");
			String code="TH"+sdf.format(date);
			String hql="from Fault where major='"+major+"' and subwaystate='已恢复' and code like '"+code+"%'";
			Query query=getSession().createQuery(hql);
			return query.list().size();
		}
		//统计指定月份指定部门未恢复的故障数目
		public int unSolvedFault4Month(String major,Date date){
			SimpleDateFormat sdf=new SimpleDateFormat("yyMM");
			String code="TH"+sdf.format(date);
			String hql="from Fault where major='"+major+"' and subwaystate='未恢复' and code like '"+code+"%'";
			Query query=getSession().createQuery(hql);
			return query.list().size();
		}
		
		//返回指定时间段内的错误单
		public List<Fault> getDailyFaults(Date dateBegin ,Date dateEnd){
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String hql="from Fault where pdate between '"+dft.format(dateBegin)+"'  and  '"+dft.format(dateEnd)+"'";
			Query query=getSession().createQuery(hql);
			List<Fault> list=query.list();
			return list;
		}
		//返回至指定时间未解决的错误单
		public List<Fault> getUnsolvedFaults(Date dateBegin ,Date dateEnd){
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String hql="from Fault where pdate between '"+dft.format(dateBegin)+"'  and  '"+dft.format(dateEnd)+"' and subwaystate='未恢复'";
			Query query=getSession().createQuery(hql);
			List<Fault> list=query.list();
			return list;
		}
		
		
	
	
	
	

	
	
	public static void main(String[] args) throws ParseException {
		DayReportBean drb =new DayReportBean();
		Date date=new SimpleDateFormat("yyyyMMdd").parse("20140806");
		System.out.println(drb.solvedFault4Day("通信", date));
	}
	
}
