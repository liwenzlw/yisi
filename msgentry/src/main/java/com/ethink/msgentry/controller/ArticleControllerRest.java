package com.ethink.msgentry.controller;

import java.util.Date;

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

import com.ethink.msgentry.bean.Article;
import com.ethink.msgentry.service.ArticleService;

@Controller
public class ArticleControllerRest {

	private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;
	
	@ResponseBody
	@RequestMapping(value="article", method=RequestMethod.POST)
	public String articlePost(Article article, MultipartFile imgFile,String _method, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		//更新修改时间
		article.setUpdateTime(new Date());
		Boolean result =false;
		if("PUT".equalsIgnoreCase(_method)) {
			// 修改数据库，和图片
			if(article.getAudit() != null) {
				article.setAuditTime(new Date());
			}
			result = articleService.updateArticle(article, imgFile, realPath);
		} else {
			// 上传图片，并将文章插入到数据库
			result = articleService.insertArticle(article, imgFile, realPath);
		}
		return String.valueOf(result);
	}
	
}
