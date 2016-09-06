package com.ethink.msgentry.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ethink.msgentry.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,HttpServletRequest request) {
		
		request.getSession().setAttribute("isAdmin", false);
		//通过用户名去数据库查找用户信息
		boolean result = loginService.login(username,password,request);
		if(result) {
			return "articleHome";
		}
		return "forward:login.jsp";
	}
	
	@RequestMapping(value="/getEidtPage")
	public String login(HttpServletRequest request) {
		
		//通过用户名去数据库查找用户信息
		if((Boolean)request.getSession().getAttribute("isLogin")) {
			return "editArticle";
		}
		return "forward:login.jsp";
	}
}
