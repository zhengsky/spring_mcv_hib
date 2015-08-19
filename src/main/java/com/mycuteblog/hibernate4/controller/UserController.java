package com.mycuteblog.hibernate4.controller;

import org.springframework.web.servlet.ModelAndView;

import com.mycuteblog.hibernate4.model.User;

/**
 * 
 * @author zhengwsa
 *
 */
public interface UserController {
    boolean addUser(User user);

    ModelAndView getUser(String userId);

    boolean updateUser(User user);

    boolean deleteUser(User user);
}
