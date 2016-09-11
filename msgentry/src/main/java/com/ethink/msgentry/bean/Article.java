package com.ethink.msgentry.bean;

import java.sql.Time;
import java.util.Date;

/**
 * 文章
 * 
 * @author liwen
 * @version 1.0
 */
public class Article {

	private Integer id;

	private Integer subType;
	private String subTypeName;

	private Integer topType;
	private String topTypeName;

	private String title;

	private String iconAddress;

	private String content;

	private String address;

	private String phone;

	private String startTime;

	private String endTime;

	private Integer audit;

	private Date uploadTime;

	private Date updateTime;

	private Date auditTime;
	
	private String showTime;
	private String auditName;
	private String updatorName;
	private String inserterName;
	

	public String getInserterName() {
		return inserterName;
	}

	public void setInserterName(String inserterName) {
		this.inserterName = inserterName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubType() {
		return subType;
	}

	public void setSubType(Integer subType) {
		this.subType = subType;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getUpdatorName() {
		return updatorName;
	}

	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}

	public String getSubTypeName() {
		return subTypeName;
	}

	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}

	public Integer getTopType() {
		return topType;
	}

	public void setTopType(Integer topType) {
		this.topType = topType;
	}

	public String getTopTypeName() {
		return topTypeName;
	}

	public void setTopTypeName(String topTypeName) {
		this.topTypeName = topTypeName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIconAddress() {
		return iconAddress;
	}

	public void setIconAddress(String iconAddress) {
		this.iconAddress = iconAddress;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		String[] piles = startTime.split(":");
		startTime = piles[0] +":"+ piles[1];
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {

		String[] piles = endTime.split(":");
		endTime = piles[0] +":"+ piles[1];
		
		this.endTime = endTime;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
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

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

}