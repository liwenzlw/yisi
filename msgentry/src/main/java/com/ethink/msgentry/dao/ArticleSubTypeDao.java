package com.ethink.msgentry.dao;

import java.util.List;

import com.ethink.msgentry.bean.ArticleSubType;

public interface ArticleSubTypeDao {
    int deleteByPrimaryKey(Integer id);

    ArticleSubType selectByPrimaryKey(Integer id);

	List<ArticleSubType> selectByPtype(int rootTypeId);

	List<ArticleSubType> getSiblingsById(int id);
}