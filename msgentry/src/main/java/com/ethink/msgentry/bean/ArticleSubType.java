package com.ethink.msgentry.bean;

/**
 * 文章二级分类
 * 
 * @author liwen
 * @version 1.0
 */
public class ArticleSubType {
	private Integer id;

	private String name;

	private Integer ptype;

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

	public Integer getPtype() {
		return ptype;
	}

	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}
}