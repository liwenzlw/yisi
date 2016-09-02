package com.ethink.msgentry.bean;

/**
 * 角色-权限
 * 
 * @author liwen
 * @version 1.0
 */
public class RolePermission {
	private Integer permission;

	private Integer role;

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}