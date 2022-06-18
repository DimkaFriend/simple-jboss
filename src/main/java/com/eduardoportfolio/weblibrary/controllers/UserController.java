package com.eduardoportfolio.weblibrary.controllers;


import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eduardoportfolio.weblibrary.dao.RoleDao;
import com.eduardoportfolio.weblibrary.dao.UserDao;
import com.eduardoportfolio.weblibrary.infra.RolePropertyEditor;
import com.eduardoportfolio.weblibrary.models.Role;
import com.eduardoportfolio.weblibrary.models.Users;

@Controller
@Transactional
@RequestMapping("/register")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class UserController {
	
	//Treat the Data from the view to the controller, passed by the checkboxes.
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Role.class, new RolePropertyEditor(roleDao));
	}
	
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;

	@RequestMapping(value="userForm", name="userForm")
	public ModelAndView userForm(Users user) {
		ModelAndView modelAndView = new ModelAndView("user/userForm");
		modelAndView.addObject("roleList", roleDao.list());
		return modelAndView;
	}
	

	@RequestMapping (value="saveUser", method=RequestMethod.POST, name="saveUser")
	public ModelAndView saveUser(@Valid Users user, BindingResult bindingResult, 
													RedirectAttributes redirectAttributes) {
		
		System.out.println(user);
		if (bindingResult.hasErrors()){
			return userForm(user);
		}
		userDao.save(user);
		
		redirectAttributes.addFlashAttribute("success", "User successfully registered");
		return new ModelAndView("redirect:/register/userForm");
	}
}
