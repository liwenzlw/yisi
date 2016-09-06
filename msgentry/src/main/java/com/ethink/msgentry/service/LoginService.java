package com.ethink.msgentry.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

	public boolean login(String username, String password, HttpServletRequest request);
	
}
