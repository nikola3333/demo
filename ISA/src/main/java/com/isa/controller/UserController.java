package com.isa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.User;
import com.isa.entity.UserLogin;
import com.isa.exceptions.UserNotFoundException;
import com.isa.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	private User findUserByEmail(@RequestBody UserLogin userLogin){
		User user = userService.findUserByEmail(userLogin.getEmail(), userLogin.getPassword());
		if(user == null)throw new UserNotFoundException(userLogin.getEmail());
		
		session.setAttribute("user", user);
		return user;
	}
	
}
