package com.ethink.msgentry.dao;

import com.ethink.msgentry.bean.Role;

public interface RoleDao {
    int deleteByPrimaryKey(Integer id);

    Role selectByPrimaryKey(Integer id);
}