package com.mycuteblog.hibernate4.controller.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mycuteblog.hibernate4.dao.UserDao;
import com.mycuteblog.hibernate4.model.User;
import com.mycuteblog.util.UserException;

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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(String username,String password) {
		System.out.println("用户名"+username);
		if(!username.equals("admin")){
			throw new UserException("用户名不正确");
		}
		System.out.println("密码"+password);
		if(!password.equals("admin")){
			throw new UserException("密码不正确");
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user_add");
		return mv;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addUser() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user_add");
		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(User user,MultipartFile attach,HttpSession session) throws Exception, IOException {
		userDao.addUser(user);
		// return userDao.addUser(user);
		String filepath = session.getServletContext().getRealPath("upload");
		System.out.println(filepath);
		File file = new File(filepath+"/"+attach.getOriginalFilename());
//		FileCopyUtils.copy(attach.getInputStream(), file);
		attach.transferTo(file);
		System.out.println(attach.getOriginalFilename()+"  "+attach.getContentType() +"  "+attach.getName());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user_add");
		return mv;
	}

	@RequestMapping(value = "/{id}/get", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("id") long id,Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", userDao.getUser(id));
		mv.setViewName("user");
		System.out.println("jsons");
		return mv;
	}

	@RequestMapping(value = "/{id}/get", method = RequestMethod.GET,params="json")
	@ResponseBody
	public User getUser(@PathVariable("id") long id) {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("user", userDao.getUser(id));
//		mv.setViewName("user");
//		return mv;
		System.out.println("json");
		return userDao.getUser(100);
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
    @RequestMapping(value = "/hello3")  
    public String hello3(HttpServletRequest request){  
        String id = request.getParameter("id");  
        System.out.println("hello3 action:"+id);  
        return "hello";  
    }  

	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	public boolean deleteUser(User user) {
		return userDao.deleteUser(user);
	}
	//局部异常处理
//	@ExceptionHandler({UserException.class})   
//    public String exception(UserException e) {   
//        System.out.println(e.getMessage()+"ddds");   
////        e.printStackTrace();   
//        return "error";   
//    }   
}
