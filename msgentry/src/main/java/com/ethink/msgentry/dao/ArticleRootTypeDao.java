package com.ethink.msgentry.dao;

import java.util.List;

import com.ethink.msgentry.bean.ArticleRootType;

public interface ArticleRootTypeDao {
    int deleteByPrimaryKey(Integer id);

    ArticleRootType selectByPrimaryKey(Integer id);

	List<ArticleRootType> selectAll();
}