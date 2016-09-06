package com.ethink.msgentry.dao;

import java.util.List;
import java.util.Map;

import com.ethink.msgentry.bean.Article;
import com.ethink.msgentry.bean.ArticleSubType;
import com.ethink.msgentry.bean.PageInfo;

public interface ArticleDao {
	
	Article selectByPrimaryKey(int id);
	
	Integer deleteByPrimaryKey(int id);

	Integer insertArticle(Article article);

	List<Article> selectArticleListByPageInfo(PageInfo pageInfo);

	Integer getRecords();
	
	Integer getRecordsInType(int type);

	String selectContentById(int id);

	List<Article> selectArticleListByTypeInPage(Map<String, Object> params);

	int updateArticle(Article article);

	List<Article> getArticleListBySubTypeInPageByParams(Map<String, Object> params);

	int getRecordsInASubType(int subType);

	int getRecordsInATopType(int topType);

	List<Article> getArticleListByTopTypeInPageByParams(Map<String, Object> params);
}