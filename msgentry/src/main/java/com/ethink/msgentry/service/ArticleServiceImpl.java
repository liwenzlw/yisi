package com.ethink.msgentry.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ethink.msgentry.bean.Article;
import com.ethink.msgentry.bean.ArticleTopType;
import com.ethink.msgentry.bean.ArticleSubType;
import com.ethink.msgentry.bean.PageInfo;
import com.ethink.msgentry.dao.ArticleDao;
import com.ethink.msgentry.dao.ArticleTopTypeDao;
import com.ethink.msgentry.dao.ArticleSubTypeDao;
import com.ethink.msgentry.util.UploadFileUtil;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ArticleTopTypeDao articleTopTypeDao;
	@Autowired
	private ArticleSubTypeDao articleSubTypeDao;

	@Override
	public String getContentById(int id) {
		String articleContent = articleDao.selectContentById(id);
		return articleContent;
	}

	@Override
	public boolean removeArticle(int id) {
		int count = articleDao.deleteByPrimaryKey(id);
		return count == 0 ? false : true;
	}

	@Override
	public String selectById(int id) {
		Article article = articleDao.selectByPrimaryKey(id);
		return JSON.toJSONStringWithDateFormat(article, "yyyy:MM:dd hh:mm:ss");
	}

	@Override
	public boolean insertArticle(Article article, MultipartFile imgFile, String realPath) {
		String iconAddress = UploadFileUtil.saveImage(imgFile, realPath);
		if("error".equalsIgnoreCase(iconAddress)) {
			return false;
		}
		article.setIconAddress(iconAddress);
		int count = articleDao.insertArticle(article);
		return count == 0 ? false : true;
	}

	@Override
	public String queryArticleByPageInfo(PageInfo pageInfo, String basePath) {
		// 根据分页和排序条件查询数据
		if (StringUtils.isEmpty(pageInfo.getSidx())) {
			pageInfo.setSidx("id");
		}
		if (StringUtils.isEmpty(pageInfo.getSord())) {
			pageInfo.setSord("asc");
		}

		List<Article> articles = articleDao.selectArticleListByPageInfo(pageInfo);
		for (Article article : articles) {
			// foodArticle.setImgAddress("<img src='" + basePath +
			// foodArticle.getImgAddress()+"'/>");
			article.setIconAddress(basePath + article.getIconAddress());
		}
		// 查询总记录数
		int records = articleDao.getRecords();
		int total = records % pageInfo.getRows() == 0 ? records / pageInfo.getRows() : records / pageInfo.getRows() + 1;
		int page = pageInfo.getPage();
		JSONObject json = new JSONObject();
		json.put("records", records);
		json.put("total", total);
		json.put("page", page);
		json.put("rows", JSON.parseArray(JSON.toJSONStringWithDateFormat(articles, "yyyy:MM:dd HH:mm:ss")));

		String retdata = JSON.toJSONStringWithDateFormat(json, "yyyy:MM:dd HH:mm:ss");
		return retdata;
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(2);
		pageInfo.setRows(10);
		pageInfo.setSord("desc");
		pageInfo.setSidx("id");
		String data = context.getBean(ArticleServiceImpl.class).queryArticleByPageInfo(pageInfo, "");
		System.out.println(data);
	}

	@Override
	public List<ArticleTopType> getRootType() {
		List<ArticleTopType> rootTypes = articleTopTypeDao.selectAll();
		return rootTypes;
	}

	@Override
	public List<ArticleSubType> getSubTypeByRootType(int rootTypeId) {
		List<ArticleSubType> subTypes = articleSubTypeDao.selectByPtype(rootTypeId);
		return subTypes;
	}

	@Override
	public String queryArticleByTypeInPage(PageInfo pageInfo, String basePath, int type) {

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", type);
		params.put("sidx", pageInfo.getSidx());
		params.put("sord", pageInfo.getSord());
		params.put("endIndex", pageInfo.getEndIndex());
		params.put("startIndex", pageInfo.getStartIndex());
		
		List<Article> articles = articleDao.selectArticleListByTypeInPage(params);
		for (Article article : articles) {
			// foodArticle.setImgAddress("<img src='" + basePath +
			// foodArticle.getImgAddress()+"'/>");
			article.setIconAddress(basePath + article.getIconAddress());
		}
		// 查询总记录数
		int records = articleDao.getRecordsInType(type);
		//total总共多少页
		int total = records % pageInfo.getRows() == 0 ? records / pageInfo.getRows() : records / pageInfo.getRows() + 1;
		int page = pageInfo.getPage();
		JSONObject json = new JSONObject();
		json.put("records", records);
		json.put("total", total);
		json.put("page", page);
		json.put("rows", JSON.parseArray(JSON.toJSONStringWithDateFormat(articles, "yyyy:MM:dd HH:mm:ss")));

		String retdata = JSON.toJSONStringWithDateFormat(json, "yyyy:MM:dd HH:mm:ss");
		return retdata;
	}

	@Override
	public Boolean updateArticle(Article article, MultipartFile imgFile, String realPath) {
		//1.如果imgFile不为空则修改图片
		if(!imgFile.isEmpty()) {
			String iconAddress = UploadFileUtil.saveImage(imgFile, realPath);
			article.setIconAddress(iconAddress);
		}
		int count = articleDao.updateArticle(article);
		return count == 0 ? false : true;
	}

	@Override
	public List<ArticleSubType> getSiblingsById(int id) {
		List<ArticleSubType>  subTypes= articleSubTypeDao.getSiblingsById(id);
		return subTypes;
	}

	@Override
	public String queryArticleListBySubTypeAndPage(PageInfo pageInfo, String basePath, int subType) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("subType", subType);
		params.put("sidx", pageInfo.getSidx());
		params.put("sord", pageInfo.getSord());
		params.put("endIndex", pageInfo.getEndIndex());
		params.put("startIndex", pageInfo.getStartIndex());
			
		List<Article>  articles= articleDao.getArticleListBySubTypeInPageByParams(params);
		
		for (Article article : articles) {
			// foodArticle.setImgAddress("<img src='" + basePath +
			// foodArticle.getImgAddress()+"'/>");
			article.setIconAddress(basePath + article.getIconAddress());
		}
		// 查询总记录数
		int records = articleDao.getRecordsInASubType(subType);
		//total总共多少页
		int total = records % pageInfo.getRows() == 0 ? records / pageInfo.getRows() : records / pageInfo.getRows() + 1;
		int page = pageInfo.getPage();
		JSONObject json = new JSONObject();
		json.put("records", records);
		json.put("total", total);
		json.put("page", page);
		json.put("rows", JSON.parseArray(JSON.toJSONStringWithDateFormat(articles, "yyyy:MM:dd")));

		String retdata = JSON.toJSONStringWithDateFormat(json, "yyyy:MM:dd");
		return retdata;
	}

	@Override
	public String queryArticleListByTopTypeAndPage(PageInfo pageInfo, String basePath, int topType) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("topType", topType);
		params.put("sidx", pageInfo.getSidx());
		params.put("sord", pageInfo.getSord());
		params.put("endIndex", pageInfo.getEndIndex());
		params.put("startIndex", pageInfo.getStartIndex());
			
		List<Article>  articles= articleDao.getArticleListByTopTypeInPageByParams(params);
		
		for (Article article : articles) {
			// foodArticle.setImgAddress("<img src='" + basePath +
			// foodArticle.getImgAddress()+"'/>");
			article.setIconAddress(basePath + article.getIconAddress());
		}
		// 查询总记录数
		int records = articleDao.getRecordsInATopType(topType);
		//total总共多少页
		int total = records % pageInfo.getRows() == 0 ? records / pageInfo.getRows() : records / pageInfo.getRows() + 1;
		int page = pageInfo.getPage();
		JSONObject json = new JSONObject();
		json.put("records", records);
		json.put("total", total);
		json.put("page", page);
		json.put("rows", JSON.parseArray(JSON.toJSONStringWithDateFormat(articles, "yyyy:MM:dd")));

		String retdata = JSON.toJSONStringWithDateFormat(json, "yyyy:MM:dd");
		return retdata;
	}

	@Override
	public Article getArticleDetailsById(int id) {
		Article article = articleDao.getArticleDetailsById(id);
		return article;
	}
}
