package com.ethink.msgentry.dao;

import java.util.List;

import com.ethink.msgentry.bean.ArticleTopType;

public interface ArticleTopTypeDao {
    int deleteByPrimaryKey(Integer id);

    ArticleTopType selectByPrimaryKey(Integer id);

	List<ArticleTopType> selectAll();
}