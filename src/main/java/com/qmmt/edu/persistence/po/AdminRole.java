package com.qmmt.edu.persistence.po;

import com.qmmt.edu.persistence.base.DBRecord;

public class AdminRole extends DBRecord {

	private Integer id;

	private String roleName;

	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}