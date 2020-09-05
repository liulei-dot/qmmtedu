package com.qmmt.edu.persistence.po;

import java.util.Date;

import com.qmmt.edu.persistence.base.DBRecord;

public class EduTeacher extends DBRecord {

	private Integer id;

	private String techerName;

	private String headerUrl;

	private String info;

	private Date createTime;

	private Date modifyTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTecherName() {
		return techerName;
	}

	public void setTecherName(String techerName) {
		this.techerName = techerName;
	}

	public String getHeaderUrl() {
		return headerUrl;
	}

	public void setHeaderUrl(String headerUrl) {
		this.headerUrl = headerUrl;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}