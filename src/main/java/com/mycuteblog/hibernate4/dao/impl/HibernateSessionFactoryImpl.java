package com.mycuteblog.hibernate4.dao.impl;

import com.mycuteblog.hibernate4.dao.HibernateSessionFactory;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author zhengwsa
 *
 */
@Service
public class HibernateSessionFactoryImpl implements HibernateSessionFactory {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
