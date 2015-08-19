package com.mycuteblog.hibernate4.controller.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycuteblog.hibernate4.dao.UserDao;
import com.mycuteblog.hibernate4.model.User;

/**
 * 
 * @author zhengwsa
 *
 */

@Controller
@RequestMapping("/user")
@Transactional
public class UserControllerImpl {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addUser() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user_add");
		return mv;
	}

	

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(User user) {
		userDao.addUser(user);
		// return userDao.addUser(user);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user_add");
		return mv;
	}

	@RequestMapping(value = "/{id}/get", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", userDao.getUser(id));
		mv.setViewName("user");
		return mv;
	}

	@RequestMapping(value = "/hello")
	public String hello(@RequestParam("username") int id) {// getParameter()的方式
		System.out.println("hello action:" + id);
		// return "hello";
		return "redirect:/hello.jsp";// 不能重定向web-info里面的文件,而且需要写上绝对路径
	}
	// 返回页面参数的第二种方式,在形参中放入一个Model
	@RequestMapping(value = "/get")
	public String getUser(Model model) {
		int id = 2;
		System.out.println("hello2 action:" + id);
		model.addAttribute("name", "huangjie");
		// 这个只有值没有键的情况下,使用Object的类型作为key,String-->string
		model.addAttribute("ok");
		return "hello";
	}
	
	//得到request,response,session等,只要在方法形参中声明参数即可  
//    @RequestMapping(value = "/hello3")  
//    public String hello3(){  
//        String id = request.getParameter("id");  
//        System.out.println("hello3 action:"+id);  
//        return "hello";  
//    }  

	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	public boolean deleteUser(User user) {
		return userDao.deleteUser(user);
	}
}
