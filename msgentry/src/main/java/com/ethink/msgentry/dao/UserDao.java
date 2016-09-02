package com.ethink.msgentry.dao;

import com.ethink.msgentry.bean.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    User selectByPrimaryKey(Integer id);
}