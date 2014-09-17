package com.subway.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.subway.domain.PageBean;



/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	/*public Session getSession() {
		return HibernateSessionFactory.getSession();
	}*/
	private Session session;
	public Session getSession() {
//		HibernateSessionFactory.closeSession();
//		HibernateSessionFactory.rebuildSessionFactory();
		session= HibernateSessionFactory.getSession();
		session.clear();
		return session;
		
	}
	
	@Override
	public List queryForPage(Query query, PageBean pagebean){
		// TODO Auto-generated method stub
		System.out.println("test....1");
		System.out.println(query==null);
		System.out.println("test....2");
		pagebean.setTotalRecords(query.list().size());
		query.setFirstResult(pagebean.getPageSize()*(pagebean.getPageIndex()-1));
		query.setMaxResults(pagebean.getPageSize());
		List list = query.list();
		return list;
	}
	
}