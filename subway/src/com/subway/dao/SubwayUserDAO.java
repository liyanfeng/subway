package com.subway.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subway.domain.PageBean;
import com.subway.domain.SubwayUser;
import com.subway.domain.UserSearchVO;

/**
 * A data access object (DAO) providing persistence and search support for
 * SubwayUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see entity.SubwayUser
 * @author MyEclipse Persistence Tools
 */

public class SubwayUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SubwayUserDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String USERNAME = "username";
	public static final String PWD = "pwd";
	public static final String MAJOR = "major";
	public static final String USEROWNER = "userowner";
	public static final String AUTHEN = "authen";

	public void save(SubwayUser transientInstance) {
		log.debug("saving SubwayUser instance");
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

	public void delete(SubwayUser persistentInstance) {
		log.debug("deleting SubwayUser instance");
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

	public SubwayUser findById(java.lang.Integer id) {
		log.debug("getting SubwayUser instance with id: " + id);
		try {
			SubwayUser instance = (SubwayUser) getSession().get(
					"com.subway.domain.SubwayUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SubwayUser instance) {
		log.debug("finding SubwayUser instance by example");
		try {
			List results = getSession().createCriteria("SubwayUser")
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
		log.debug("finding SubwayUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SubwayUser as model where model."
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

	public List findByPwd(Object pwd) {
		return findByProperty(PWD, pwd);
	}

	public List findByMajor(Object major) {
		return findByProperty(MAJOR, major);
	}

	public List findByUserowner(Object userowner) {
		return findByProperty(USEROWNER, userowner);
	}

	public List findByAuthen(Object authen) {
		return findByProperty(AUTHEN, authen);
	}

	public List findAll() {
		log.debug("finding all SubwayUser instances");
		try {
			String queryString = "from SubwayUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<SubwayUser> getSomeInfo(int start,int sum) {
		log.debug("finding all SubwayUser instances");
		try {
			String queryString = "from SubwayUser order by id asc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(sum);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<String> findAllInfo(String param) {
		log.debug("finding all SubwayUser instances");
		try {
			String queryString = "select distinct "+param+" from subway_user";
			Query queryObject = getSession().createSQLQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public String clearDBTable()
	{
		String queryString = "truncate table subway_user";
		
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
	
	public String backupdb()
	{
		Date d=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddHHmmss");
		//System.out.println(sf.format(d));
		String queryString = "BACKUP DATABASE SUBWAY TO DISK = 'd:/subway.bak'";
		String queryStringback = "BACKUP DATABASE SUBWAY TO DISK = 'd:/subway_"+sf.format(d)+".bak'";
		
		@SuppressWarnings("deprecation")
		Connection conn = getSession().connection();//.createSQLQuery(queryString);
		try {
			Statement stst=conn.createStatement();
			stst.execute(queryString);
			stst.execute(queryStringback);
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
	
	public String restoredb()
	{
		String queryString = "use master RESTORE DATABASE subway FROM DISK = 'd:/subway.bak' WITH REPLACE,MOVE 'subway' TO 'C:/Program Files/Microsoft SQL Server/MSSQL10.SQLEXPRESS/MSSQL/DATA/subway.mdf',MOVE 'subway_log' TO 'C:/Program Files/Microsoft SQL Server/MSSQL10.SQLEXPRESS/MSSQL/DATA/subway.ldf'";
		@SuppressWarnings("deprecation")
		Connection conn = getSession().connection();//.createSQLQuery(queryString);
		try {
			Statement stst=conn.createStatement();
		
			stst.execute(queryString);
			return "OK";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "ERROR";
		
	}

	public SubwayUser merge(SubwayUser detachedInstance) {
		log.debug("merging SubwayUser instance");
		try {
			SubwayUser result = (SubwayUser) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SubwayUser instance) {
		log.debug("attaching dirty SubwayUser instance");
		try {
			Session session=getSession();
			Transaction txt=session.beginTransaction();
			session.update(instance);
			txt.commit();
			session.close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SubwayUser instance) {
		log.debug("attaching clean SubwayUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public PageBean<SubwayUser> findPage(PageBean<SubwayUser> pagebean) {
		
		try {
			String queryString = "from SubwayUser";
			Query queryObject = getSession().createQuery(queryString);			
			pagebean.setDatas(queryForPage(queryObject, pagebean));
			return pagebean;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public SubwayUser findByUserName(String username) {
		return findByProperty(USERNAME, username).size()>0?(SubwayUser)(findByProperty(USERNAME, username).get(0)):null;
//		(SubwayUser)(findByProperty(USERNAME, username).get(0))
	}

	public PageBean<SubwayUser> findByLimit(UserSearchVO vo, PageBean<SubwayUser> pageBean) {
		System.out.println("query happen1");
		StringBuffer hql = new StringBuffer("from SubwayUser as user  where 1=1 ");
		if (vo.getName() != null && !vo.getName().trim().equals("")) {
			hql.append(" and user.name like '%" + vo.getName().trim() + "%' ");
		}
		if (vo.getMajor() != null && !vo.getMajor().trim().equals("")) {
			hql.append(" and user.major like '%" + vo.getMajor().trim() + "%' ");
		}
		if (vo.getAuthen() != null &&vo.getAuthen()!=null) {
			hql.append(" and user.authen="+vo.getAuthen());
		}
		if (vo.getUserowner()!= null && !vo.getUserowner().trim().equals("")) {
			hql.append(" and user.userowner like '%"+vo.getUserowner().trim()+ "%' ");
		}
		System.out.println("query happen2");
		
		
		hql.append(" order by id desc");
		Query query = getSession().createQuery(hql.toString());
		List<SubwayUser> list = queryForPage(query,pageBean);//query.list();
		System.out.println("query happen");
		pageBean.setDatas(list);
		return pageBean;
	}
	
}