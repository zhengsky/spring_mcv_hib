package com.mycuteblog.hibernate4.controller.impl;

import com.mycuteblog.hibernate4.controller.UserController;
import com.mycuteblog.hibernate4.dao.UserDao;
import com.mycuteblog.hibernate4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

/**
 * 
 * @author zhengwsa
 *
 */

@Controller
@RequestMapping("/user")
@Transactional
public class UserControllerImpl  {

	@Autowired
	private UserDao userDao;

	@RequestMapping("/add")
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

	@RequestMapping(value="/{id}/get",method=RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("id")long id) {
		ModelAndView mv = new ModelAndView();
        mv.addObject("user", userDao.getUser(id));
        mv.setViewName("user");
        return mv;
	}

	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	public boolean deleteUser(User user) {
		return userDao.deleteUser(user);
	}
}
