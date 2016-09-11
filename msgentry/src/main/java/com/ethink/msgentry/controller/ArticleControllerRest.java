package com.ethink.msgentry.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.ethink.msgentry.bean.Article;
import com.ethink.msgentry.bean.ArticleSubType;
import com.ethink.msgentry.bean.ArticleTopType;
import com.ethink.msgentry.bean.PageInfo;
import com.ethink.msgentry.service.ArticleService;

@Controller
public class ArticleControllerRest {

	private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	/**
	 * 上传文章
	 * 
	 * @param article
	 * @param imgFile
	 * @param _method
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "article/add", method = RequestMethod.POST)
	public String articlePost(Article article, MultipartFile imgFile, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String realPath = request.getSession().getServletContext().getRealPath("/");
		// 更新修改时间
		article.setUploadTime(new Date());
		article.setInserterName((String)request.getSession().getAttribute("user"));
		Boolean result = false;
		// 上传图片，并将文章插入到数据库
		result = articleService.insertArticle(article, imgFile, realPath);
		return String.valueOf(result);
	}

	/**
	 * 修改文章
	 * 
	 * @param article
	 * @param imgFile
	 * @param _method
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "article/edit", method = RequestMethod.POST)
	public String articleEdit(Article article, MultipartFile imgFile, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String realPath = request.getSession().getServletContext().getRealPath("/");
		// 更新修改时间
		article.setUpdateTime(new Date());
		if((Boolean)request.getSession().getAttribute("canAudit")) {
			article.setAuditName((String)request.getSession().getAttribute("user"));
		}
		article.setUpdatorName((String)request.getSession().getAttribute("user"));
		Boolean result = false;
		// 修改数据库，和图片
		if (article.getAudit() != null) {
			article.setAuditTime(new Date());
		}
		result = articleService.updateArticle(article, imgFile, realPath);
		return String.valueOf(result);
	}

	/**
	 * 根据顶层类型查询子类型
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/type/getSubTypeByTopType", produces = "application/json; charset=utf-8")
	public List<ArticleSubType> querySubTypeByRootType(int id, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<ArticleSubType> subTypes = articleService.getSubTypeByRootType(id);
		return subTypes;
	}

	/**
	 * 根据类型获取同级类型
	 * 
	 * @param id
	 * @return
	 * 
	 * @deprecated
	 */
	@ResponseBody
	@RequestMapping(value = "/type/getSiblings")
	public List<ArticleSubType> getSiblings(int id, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<ArticleSubType> subTypes = articleService.getSiblingsById(id);
		return subTypes;
	}

	/**
	 * 获取所有的顶层类型
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/type/getTopType", produces = "application/json; charset=utf-8")
	public List<ArticleTopType> queryRootType(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<ArticleTopType> rootTypes = articleService.getRootType();
		return rootTypes;
	}

	/**
	 * 根据文章的主键删除文章
	 * 
	 * @param request
	 * @param response
	 * @param id
	 *            文章主键
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/article/del")
	public String deleteArticle(HttpServletRequest request, HttpServletResponse response, int id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		boolean ret = articleService.removeArticle(id);
		return ret ? "删除成功" : "删除失败";
	}

	/**
	 * 根据分页信息查询数据
	 * 
	 * @param callback
	 * @param pageInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/article/queryArticleByPage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String queryArticleByPage(String callback, PageInfo pageInfo, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
		String data = articleService.queryArticleByPageInfo(pageInfo, basePath);
		if (!StringUtils.isEmpty(callback)) {
			data = callback + "(" + data + ")";
		}
		return data;
	}

	/**
	 * 通过文章的主键获取文章的内容
	 * 
	 * @param id
	 *            文章的主键
	 * @param response
	 */
	@RequestMapping(value = "/article/getContentById") // ,method=RequestMethod.POST
	public void getContentById(int id, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		
		String articleContent = articleService.getContentById(id);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(articleContent);
			out.flush();
			out.close();
		} catch (IOException e) {
			out.println("error");
			out.flush();
			out.close();
		} finally {
			out.close();
		}
	}
	
	/**
	 * 根据二级类型和分页信息查询文章列表数据（h5接口）
	 * 
	 * @param pageInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/article/queryArticleListBySubTypeAndPage", produces = "application/json; charset=utf-8")
	public String queryArticleListBySubTypeAndPage( int subType, PageInfo pageInfo, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
		String data = articleService.queryArticleListBySubTypeAndPage(pageInfo, basePath, subType);
		return data;
	}
	/**
	 * 根据顶级类型和分页信息查询文章列表数据（h5接口）
	 * 
	 * @param pageInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/article/queryArticleListByTopTypeAndPage", produces = "application/json; charset=utf-8")
	public String queryArticleListByTopTypeAndPage( int topType, PageInfo pageInfo, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath() + "/";
		String data = articleService.queryArticleListByTopTypeAndPage(pageInfo, basePath, topType);
		return data;
	}
	
	/**
	 * 通过文章的主键获取文章的详细信息
	 * 
	 * @param id
	 *            文章的主键
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/article/getArticleDetailsById", produces = "application/json; charset=utf-8") // ,method=RequestMethod.POST
	public String getArticleDetailsById(int id, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		Article article = articleService.getArticleDetailsById(id);
		String retData = JSON.toJSONString(article);
		return retData;
	}

}
