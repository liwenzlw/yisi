package com.ethink.msgentry.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.ethink.msgentry.bean.Article;
import com.ethink.msgentry.bean.ArticleRootType;
import com.ethink.msgentry.bean.ArticleSubType;
import com.ethink.msgentry.bean.PageInfo;
import com.ethink.msgentry.service.ArticleService;

@Controller
@RequestMapping("/foodArticle")
public class ArticleController {

	private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	/**
	 * 根据类型和分页信息查询文章数据
	 * 
	 * @param callback
	 * @param pageInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryArticleByTypeInPage", produces = "application/json; charset=utf-8")
	public String queryArticleByTypeInPage(String callback, int type, PageInfo pageInfo, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
		String data = articleService.queryArticleByTypeInPage(pageInfo, basePath, type);
		return data;
	}

	/**
	 * 根据顶层类型查询子类型
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSubTypeByRootType")
	public List<ArticleSubType> querySubTypeByRootType(int id,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<ArticleSubType> subTypes = articleService.getSubTypeByRootType(id);
		return subTypes;
	}

	/**
	 * 获取所有的顶层类型
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRootType")
	public List<ArticleRootType> queryRootType(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<ArticleRootType> rootTypes = articleService.getRootType();
		return rootTypes;
	}

	/**
	 * 通过文章的主键获取文章的内容
	 * 
	 * @param id
	 *            文章的主键
	 * @param response
	 */
	@RequestMapping(value = "/getContentById") // ,method=RequestMethod.POST
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
	 * 根据文章的主键删除文章
	 * 
	 * @param request
	 * @param response
	 * @param id
	 *            文章主键
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryArticleByPage", method = RequestMethod.DELETE)
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
	@RequestMapping(value = "/queryArticleByPage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
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
	 * 上传文章
	 * 
	 * @param article
	 * @param imgFile
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String uploadArticle(MultipartFile imgFile, Article article, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		// 上传图片，并将文章插入到数据库
		Boolean result = articleService.insertArticle(article, imgFile, realPath);

		return String.valueOf(result);
	}

	/**
	 * 文件过大处理
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler
	public String HandelException(HttpServletRequest request, Exception e) {
		if (e instanceof MaxUploadSizeExceededException) {
			return "文件超出最大范围";
		}
		return null;
	}

}
