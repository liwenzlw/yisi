package com.ethink.msgentry.bean;

import java.util.Date;

/**
 * 文章
 * 
 * @author liwen
 * @version 1.0
 */
public class Article {
	
	private Integer id;

	private String type;

	private String title;

	private String iconAddress;

	private Boolean audit;

	private Date uploadTime;

	private Date updateTime;

	private Date auditTime;

	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getIconAddress() {
		return iconAddress;
	}

	public void setIconAddress(String iconAddress) {
		this.iconAddress = iconAddress == null ? null : iconAddress.trim();
	}

	public Boolean getAudit() {
		return audit;
	}

	public void setAudit(Boolean audit) {
		this.audit = audit;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}