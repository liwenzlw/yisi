package com.ethink.msgentry.dao;

import com.ethink.msgentry.bean.User;

public interface UserDao {

	User getUserInfoByUsername(String username);
}