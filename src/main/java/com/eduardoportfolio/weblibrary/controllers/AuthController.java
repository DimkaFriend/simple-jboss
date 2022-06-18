package com.eduardoportfolio.weblibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eduardoportfolio.weblibrary.dao.UserDao;
import com.eduardoportfolio.weblibrary.models.Users;

@Controller
public class AuthController {
	@Autowired
	UserDao userDao;
	
//	//Binding in the SecurityConfiguration.
//	@RequestMapping("/login")
//	public String loginPage(){
//		return "auth/login";
//	}
	
	//Binding in the SecurityConfiguration.
	@RequestMapping(name = "/signup", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<UserDetails> registerUser(Users user) throws Exception{
		userDao.createUser(user);
		return new ResponseEntity<UserDetails>(userDao.loadUserByUsername(user.getLogin()),HttpStatus.OK);
	}
	
	@RequestMapping(name = "/signupjson", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Users registerUserJson(Users user) throws Exception{
		userDao.createUser(user);
		return user;
	}
}
