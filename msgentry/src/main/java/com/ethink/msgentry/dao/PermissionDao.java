package com.ethink.msgentry.dao;

import com.ethink.msgentry.bean.Permission;

public interface PermissionDao {
    int deleteByPrimaryKey(Integer id);

    Permission selectByPrimaryKey(Integer id);
}