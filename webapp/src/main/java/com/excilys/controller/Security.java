package com.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Security {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
//	@RequestMapping(value="/loginPage", method=RequestMethod.POST)
//	public String loginPost() {
//		return "dashboard";
//	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		return "logout";
	}
}
