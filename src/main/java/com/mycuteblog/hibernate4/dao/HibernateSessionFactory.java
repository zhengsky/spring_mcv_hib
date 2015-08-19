package com.mycuteblog.hibernate4.dao;

import org.hibernate.Session;

/**
 * 
 * @author zhengwsa
 *
 */
public interface HibernateSessionFactory {

	Session getSession();
}
