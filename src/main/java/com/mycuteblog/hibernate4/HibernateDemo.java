package com.mycuteblog.hibernate4;

import com.mycuteblog.hibernate4.controller.UserController;
import com.mycuteblog.hibernate4.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * 
 * @author zhengwsa
 *
 */
public class HibernateDemo {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");

		UserController userController = (UserController) context.getBean("userController");

		User user = new User();
		user.setUserId(100);
		user.setFirstName("Foo");
		user.setLastName("Boo");
		user.setAge(25);
		user.setCreatedDate(new Date());

		userController.addUser(user);

//		User savedUser = userController.getUser(100);
//		System.out.println(savedUser.toString());
//
//		user.setFirstName("Boo");
//		user.setLastName("Foo");
//		user.setAge(24);
//		userController.updateUser(user);
//
//		User updatedUser = userController.getUser(100);
//		System.out.println(updatedUser.toString());

//		userController.deleteUser(updatedUser);
//
//		User deletedUser = userController.getUser(100);
//
//		if (deletedUser == null) {
//			System.out.println("user has been deleted from db");
//		}

	}
}
