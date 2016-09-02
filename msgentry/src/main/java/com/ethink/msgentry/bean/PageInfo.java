package com.ethink.msgentry.bean;

/**
 * 分页信息
 * 
 * @author liwen
 * @version 1.0
 */
public class PageInfo {

	// 单页显示行数
	private int rows = 20;
	// 当前页
	private int page = 1;
	// 排序规则
	private String sord = "asc";
	// 排序的列名
	private String sidx = "id";

	// 开始记录的索引
	private int startIndex;
	// 结束记录的索引
	private int endIndex;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public int getStartIndex() {
		return rows * (page - 1);
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return rows * page;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
}
