package com.ethink.msgentry.bean;

/**
 * 文章顶级分类
 * 
 * @author liwen
 * @version 1.0
 */
public class ArticleTopType {
	
	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}