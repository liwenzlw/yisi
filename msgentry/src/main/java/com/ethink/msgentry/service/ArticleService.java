package com.ethink.msgentry.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ethink.msgentry.bean.Article;
import com.ethink.msgentry.bean.ArticleTopType;
import com.ethink.msgentry.bean.ArticleSubType;
import com.ethink.msgentry.bean.PageInfo;

public interface ArticleService {

	String selectById(int id);

	boolean insertArticle(Article article, MultipartFile imgFile, String realPath);
	
	String queryArticleByPageInfo(PageInfo pageInfo,String basePath);

	boolean removeArticle(int id);

	String getContentById(int id);

	List<ArticleTopType> getRootType();

	List<ArticleSubType> getSubTypeByRootType(int rootTypeId);

	String queryArticleByTypeInPage(PageInfo pageInfo, String basePath, int type);

	Boolean updateArticle(Article article, MultipartFile imgFile, String realPath);

	List<ArticleSubType> getSiblingsById(int id);

	String queryArticleListBySubTypeAndPage(PageInfo pageInfo, String basePath, int subType);

	String queryArticleListByTopTypeAndPage(PageInfo pageInfo, String basePath, int topType);
	
	/**
	 * 通过id获取文章界面的详细信息
	 * @param id
	 * @return
	 */
	Article getArticleDetailsById(int id);

}
