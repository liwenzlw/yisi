package com.yisi.weixin.bean;

/**
 * 公众账号分组信息
 * 
 * @position 用户管理-->用户分组管理-->查询所有分组
 * @author liwen
 * @version 1.0
 */
public class UserGroup {
	// 分组id
	private int id;
	// 分组名称
	private String name;
	// 分组内的用户数
	private int count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
