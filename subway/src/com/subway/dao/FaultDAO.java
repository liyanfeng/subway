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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subway.domain.Fault;
import com.subway.domain.FaultSearchVO;
import com.subway.domain.PageBean;

/**
 * A data access object (DAO) providing persistence and search support for Fault
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see entity.Fault
 * @author MyEclipse Persistence Tools
 */

public class FaultDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(FaultDAO.class);
	// property constants
	public static final String CODE = "code";
	public static final String GRADE = "grade";
	public static final String MAJOR = "major";
	public static final String USEROWNER = "userowner";
	public static final String FINDER = "finder";
	public static final String ACCEPTER = "accepter";
	public static final String PLACE = "place";
	public static final String PRESENT = "present";
	public static final String PROCESS = "process";
	public static final String REQ_MODIFY = "reqModify";
	public static final String REQBACK = "reqback";
	public static final String SUBWAYSTATE = "subwaystate";
	public static final String CAUSE = "cause";
	public static final String IS_CONFIRM = "isConfirm";
	public static final String CONFIRM_PEOPLE = "confirmPeople";
	public static final String GENERATE_PEOPLE = "generatePeople";
	public static final String DEVICE = "device";
	public static final String CAUSE_ANALYSE = "causeAnalyse";
	public static final String ANALYSE_CONFIRM_PEO = "analyseConfirmPeo";
	public static final String CAUSE_CONFIRM_PEO = "causeConfirmPeo";

	public void save(Fault transientInstance) {
		log.debug("saving Fault instance");
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

	public void delete(Fault persistentInstance) {
		log.debug("deleting Fault instance");
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

	public Fault findById(java.lang.Integer id) {
		log.debug("getting Fault instance with id: " + id);
		try {
			Fault instance = (Fault) getSession().get("com.subway.domain.Fault", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Fault instance) {
		log.debug("finding Fault instance by example");
		try {
			List results = getSession().createCriteria("com.subway.domain.Fault")
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
		log.debug("finding Fault instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Fault as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByGrade(Object grade) {
		return findByProperty(GRADE, grade);
	}

	public List findByMajor(Object major) {
		return findByProperty(MAJOR, major);
	}

	public List findByUserowner(Object userowner) {
		return findByProperty(USEROWNER, userowner);
	}

	public List findByFinder(Object finder) {
		return findByProperty(FINDER, finder);
	}

	public List findByAccepter(Object accepter) {
		return findByProperty(ACCEPTER, accepter);
	}

	public List findByPlace(Object place) {
		return findByProperty(PLACE, place);
	}

	public List findByPresent(Object present) {
		return findByProperty(PRESENT, present);
	}

	public List findByProcess(Object process) {
		return findByProperty(PROCESS, process);
	}

	public List findByReqModify(Object reqModify) {
		return findByProperty(REQ_MODIFY, reqModify);
	}

	public List findByReqback(Object reqback) {
		return findByProperty(REQBACK, reqback);
	}

	public List findBySubwaystate(Object subwaystate) {
		return findByProperty(SUBWAYSTATE, subwaystate);
	}

	public List findByCause(Object cause) {
		return findByProperty(CAUSE, cause);
	}

	public List findByIsConfirm(Object isConfirm) {
		return findByProperty(IS_CONFIRM, isConfirm);
	}

	public List findByConfirmPeople(Object confirmPeople) {
		return findByProperty(CONFIRM_PEOPLE, confirmPeople);
	}

	public List findByGeneratePeople(Object generatePeople) {
		return findByProperty(GENERATE_PEOPLE, generatePeople);
	}

	public List findByDevice(Object device) {
		return findByProperty(DEVICE, device);
	}

	public List findByCauseAnalyse(Object causeAnalyse) {
		return findByProperty(CAUSE_ANALYSE, causeAnalyse);
	}

	public List findByAnalyseConfirmPeo(Object analyseConfirmPeo) {
		return findByProperty(ANALYSE_CONFIRM_PEO, analyseConfirmPeo);
	}

	public List findByCauseConfirmPeo(Object causeConfirmPeo) {
		return findByProperty(CAUSE_CONFIRM_PEO, causeConfirmPeo);
	}

	@SuppressWarnings("unchecked")
	public List<Fault> findAll() {
		log.debug("finding all Fault instances");
		try {
			String queryString = "from Fault";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Fault merge(Fault detachedInstance) {
		log.debug("merging Fault instance");
		try {
			Fault result = (Fault) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Fault instance) {
		log.debug("attaching dirty Fault instance");
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

	public void attachClean(Fault instance) {
		log.debug("attaching clean Fault instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public PageBean<Fault> findPage(PageBean<Fault> pb) {
		
		try {
			String queryString = "from Fault";
			Query queryObject = getSession().createQuery(queryString);			
			pb.setDatas(queryForPage(queryObject, pb));
			return pb;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public PageBean<Fault> findPage(PageBean<Fault> pb, FaultSearchVO vo) {
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			StringBuffer queryString=new StringBuffer();
			queryString.append("from Fault where 1=1");
			if (vo!=null) {
				if (vo.getGrade()!=null&&!vo.getGrade().trim().equals("")) {
					queryString.append("  and grade='"+vo.getGrade()+"'");
				}
				//
				if (vo.getPlace()!=null&&!vo.getPlace().trim().equals("")) {
					queryString.append("  and place like '%"+vo.getPlace()+"%'");
				}
				//
				if (vo.getDevice()!=null&&!vo.getDevice().trim().equals("")) {
					queryString.append("  and device like '%"+vo.getDevice()+"%'");
				}
				
				
				if (vo.getStatus()!=null&&!vo.getStatus().trim().equals("")) {
					queryString.append("  and subwaystate='"+vo.getStatus()+"'");
				}
				
				if (vo.getIsConfirm()!=null&&!vo.getIsConfirm().trim().equals("")) {
					queryString.append("   and ( isConfirm='"+vo.getIsConfirm()+"')");
				}
					
				if (vo.getCause()!=null&&!vo.getCause().trim().equals("")) {
					queryString.append("  and cause='"+vo.getCause()+"'");
				}
				if (vo.getCode()!=null&&(!vo.getCode().trim().equals(""))) {
					queryString.append("  and code='"+vo.getCode()+"'");
				}
				if (vo.getFinder()!=null&&(!vo.getFinder().trim().equals(""))) {
					queryString.append("  and finder='"+vo.getFinder()+"'");
				}
				if (vo.getMajor()!=null&&(!vo.getMajor().trim().equals(""))) {
					queryString.append("  and major='"+vo.getMajor()+"'");
				}
				if (vo.getUserowner()!=null&&(!vo.getUserowner().trim().equals(""))) {
					queryString.append("  and userowner='"+vo.getUserowner()+"'");
				}
				if (vo.getAccepter()!=null&&(!vo.getAccepter().trim().equals(""))) {
					queryString.append("  and accepter='"+vo.getAccepter()+"'");
				}
				if (vo.getTimestart() != null &&!vo.getTimestart().trim().equals("")) {
					queryString.append(" and ptime between '"+vo.getTimestart()+"' and '");
					if (vo.getTimeend()!= null && !vo.getTimeend().trim().equals("")) {
						queryString.append(vo.getTimeend().trim()+ "' ");
					}else{
						queryString.append(sf.format(d).toString()+"'");
					}
				}
//				if (vo.getTimestart()!=null&&vo.getTimestart()!=null&&vo.getTimestart().before(vo.getTimeend())) {
//					queryString.append("  and fault.pdate between "+vo.getTimestart()+"and"+vo.getTimeend());
//				}
				
			}
			queryString.append("  order by id desc");
			Query queryObject = getSession().createQuery(queryString.toString());//get一个object【】的list
			List results=queryForPage(queryObject, pb);
			pb.setDatas(results);
			return pb;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public int getTodayNum()
	{
		log.debug("finding all Fault instances");
		try {
			Date d=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
			String pattern=df.format(d);
			System.out.println("pattern:"+pattern);
			String queryString = "from Fault where code like 'TH"+pattern+"%' order by id desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Fault> list=queryObject.list();
			if(list==null||list.size()==0)
				return 0;
			else
			{
				String codetmp=list.get(0).getCode().substring(8);
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^code:"+codetmp+"int:"+Integer.parseInt(codetmp));
				return Integer.parseInt(codetmp);
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Fault> getSomeInfo(int index, int sum) {
		log.debug("finding all SubwayUser instances");
		try {
			String queryString = "from Fault order by id asc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult(index);
			queryObject.setMaxResults(sum);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public String clearDBTable()
	{
		String queryString = "truncate table fault";
		
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
}