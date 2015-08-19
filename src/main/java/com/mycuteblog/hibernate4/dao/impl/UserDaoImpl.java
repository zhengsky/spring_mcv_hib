package com.mycuteblog.hibernate4.dao.impl;

import com.mycuteblog.hibernate4.dao.HibernateSessionFactory;
import com.mycuteblog.hibernate4.dao.UserDao;
import com.mycuteblog.hibernate4.model.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author zhengwsa
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	HibernateSessionFactory hibernateSessionFactory;

	@Override
	public boolean addUser(User user) {
		Session session = hibernateSessionFactory.getSession();
		session.save(user);
		return true;
	}

	@Override
	public User getUser(long userId) {
		Session session = hibernateSessionFactory.getSession();
		User user = (User) session.createCriteria(User.class).add(Restrictions.eq("userId", userId)).uniqueResult();

		return user;
	}

	@Override
	public boolean updateUser(User user) {
		Session session = hibernateSessionFactory.getSession();
		session.update(user);
		return true;
	}

	@Override
	public boolean deleteUser(User user) {
		Session session = hibernateSessionFactory.getSession();
		session.delete(user);
		return true;
	}
}
