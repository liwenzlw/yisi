package com.ethink.msgentry.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ethink.msgentry.bean.User;
import com.ethink.msgentry.dao.UserDao;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private UserDao userDao;

	@Override
	public boolean login( String username, String password, HttpServletRequest request) {
		
		User user = userDao.getUserInfoByUsername(username);
		
		if(null != user && user.getPassword().equals(password)) {
			if(user.getRole().equals(1)) {
				request.getSession().setAttribute("isAdmin", true);
			}
			request.getSession().setAttribute("isLogin", true);
			return true;
		}
		return false;
	}

}
