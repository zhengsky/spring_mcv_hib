package com.mycuteblog.hibernate4.dao;

import com.mycuteblog.hibernate4.model.User;

/**
 * 
 * @author zhengwsa
 *
 */
public interface UserDao {

	boolean addUser(User user);

	User getUser(long userId);

	boolean updateUser(User user);

	boolean deleteUser(User user);

}
