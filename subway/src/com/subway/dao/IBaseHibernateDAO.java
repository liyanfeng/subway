package com.subway.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.subway.domain.PageBean;


/**
 * Data access interface for domain model
 * @author MyEclipse Persistence Tools
 */
public interface IBaseHibernateDAO {
	public Session getSession();

	List queryForPage(Query query, PageBean pagebean);
}