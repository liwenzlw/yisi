package com.ethink.msgentry.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ethink.msgentry.bean.Article;
import com.ethink.msgentry.bean.ArticleRootType;
import com.ethink.msgentry.bean.ArticleSubType;
import com.ethink.msgentry.bean.PageInfo;

public interface ArticleService {

	String selectById(int id);

	boolean insertArticle(Article article, MultipartFile imgFile, String realPath);
	
	String queryArticleByPageInfo(PageInfo pageInfo,String basePath);

	boolean removeArticle(int id);

	String getContentById(int id);

	List<ArticleRootType> getRootType();

	List<ArticleSubType> getSubTypeByRootType(int rootTypeId);

	String queryArticleByTypeInPage(PageInfo pageInfo, String basePath, int type);

	Boolean updateArticle(Article article, MultipartFile imgFile, String realPath);
}
