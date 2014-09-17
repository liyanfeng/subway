package com.subway.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.mapping.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subway.domain.LogSearchVO;
import com.subway.domain.PageBean;
import com.subway.domain.SubwayUser;
import com.subway.domain.Syslog;
import com.subway.domain.UserSearchVO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Syslog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see entity.Syslog
 * @author MyEclipse Persistence Tools
 */

public class SyslogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SyslogDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String OPER = "oper";
	public static final String CONTENT = "content";

	public void save(Syslog transientInstance) {
		log.debug("saving Syslog instance");
		try {
			Session session=getSession();
			Transaction txt=session.beginTransaction();
			session.save(transientInstance);
			txt.commit();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Syslog persistentInstance) {
		log.debug("deleting Syslog instance");
		try {
			Session session=getSession();
			Transaction txt=session.beginTransaction();
			session.delete(persistentInstance);
			txt.commit();
			session.close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Syslog findById(java.lang.Integer id) {
		log.debug("getting Syslog instance with id: " + id);
		try {
			Syslog instance = (Syslog) getSession().get("com.subway.domain.Syslog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public List findByTime(String begin,String end){
				Session session=getSession();
				String sql="select * from syslog where logTime between '"+begin.toString()+"' and '"+end.toString()+"' ";
				Query q = session.createSQLQuery(sql);
				List results=q.list();//zheli fan hui de shi object shuzu meiyige doushi
				List returnlist=new ArrayList();
				for (Object object : results) {
					Object[] a = (Object[])object;
					Syslog s = new Syslog();
					s.setId(Integer.parseInt(a[0].toString()));
					s.setName(a[1].toString());
					s.setLogTime((Date)a[2]);
					s.setOper(a[3].toString());
					s.setContent(a[4].toString());
					returnlist.add(s);
				}
				session.close();
				return returnlist;//给时间进行查询
		
	}
	public List findByExample(Syslog instance) {
		log.debug("finding Syslog instance by example");
		try {
			List results = getSession().createCriteria("com.subway.domain.Syslog")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Syslog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Syslog as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByOper(Object oper) {
		return findByProperty(OPER, oper);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	@SuppressWarnings("unchecked")
	public List<Syslog> findAll() {
		log.debug("finding all Syslog instances");
		try {
			String queryString = "from Syslog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Syslog merge(Syslog detachedInstance) {
		log.debug("merging Syslog instance");
		try {
			Syslog result = (Syslog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Syslog instance) {
		log.debug("attaching dirty Syslog instance");
		try {
			Session session=getSession();
			session.saveOrUpdate(instance);
			log.debug("attach successful");
			session.close();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Syslog instance) {
		log.debug("attaching clean Syslog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public PageBean<Syslog> findPage(PageBean<Syslog> pb) {
		
		try {
			String queryString = "from Syslog order by id desc";
			Query queryObject = getSession().createQuery(queryString);			
			pb.setDatas(queryForPage(queryObject, pb));
			return pb;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<String> findAllInfo(String param) {
		log.debug("finding all syslog instances");
		try {
			String queryString = "select distinct "+param+" from syslog";
			Query queryObject = getSession().createSQLQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public PageBean<Syslog> findByLimit(LogSearchVO vo,PageBean<Syslog> pagebean){
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		StringBuffer hql = new StringBuffer("select * from syslog where 1=1 ");
		if (vo.getName() != null && !vo.getName().trim().equals("")) {
			hql.append(" and name like '%" + vo.getName().trim() + "%' ");
		}
		if (vo.getOper() != null && !vo.getOper().trim().equals("")) {
			hql.append(" and oper like '%" + vo.getOper().trim() + "%' ");
		}
		if (vo.getBegintime() != null &&!vo.getBegintime().trim().equals("")) {
			hql.append(" and logTime between '"+vo.getBegintime()+"' and '");
			if (vo.getEndtime()!= null && !vo.getEndtime().trim().equals("")) {
				hql.append(vo.getEndtime().trim()+ "' ");
			}else{
				hql.append(sf.format(d).toString()+"'");
			}
		}
		hql.append(" order by id desc ");
		System.out.println(hql+"==============");
		
		
		//hql.append(" order by id desc");
		Query query = getSession().createSQLQuery(hql.toString());
		List results = queryForPage(query,pagebean);//query.list();
		
		List returnlist=new ArrayList();
		for (Object object : results) {
			Object[] a = (Object[])object;
			Syslog s = new Syslog();
			s.setId(Integer.parseInt(a[0].toString()));
			s.setName(a[1].toString());
			s.setLogTime((Date)a[2]);
			s.setOper(a[3].toString());
			s.setContent(a[4].toString());
			System.out.println("+++++++++"+a[0]+a[1]+a[2]+a[3]+a[4]);
			returnlist.add(s);
		}
		
		System.out.println("query happen");
		pagebean.setDatas(returnlist);
		
		return pagebean;
	}
	
	public String clearDBTable()
	{
		String queryString = "truncate table syslog";
		
		@SuppressWarnings("deprecation")
		Connection conn = getSession().connection();//.createSQLQuery(queryString);
		try {
			Statement stst=conn.createStatement();
			stst.execute(queryString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "OK";
	}
	
		@SuppressWarnings("unchecked")
		public List<Syslog> getSomeInfo1(int index, int sum) {
			log.debug("finding all SubwayUser instances");
			try {
				String queryString = "from Syslog order by id asc";
				Query queryObject = getSession().createQuery(queryString);
				queryObject.setFirstResult(index);
				queryObject.setMaxResults(sum);
			return queryObject.list();
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
	
}