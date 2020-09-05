package com.qmmt.edu.persistence.po;

import java.util.Date;

import com.qmmt.edu.persistence.base.DBRecord;

public class EduStudent extends DBRecord {

	private Long id;

	private Long wxUid;

	private String mobile;

	private Date createTime;

	private Date modifyTime;

	private Long commendWxuid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWxUid() {
		return wxUid;
	}

	public void setWxUid(Long wxUid) {
		this.wxUid = wxUid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public Long getCommendWxuid() {
		return commendWxuid;
	}

	public void setCommendWxuid(Long commendWxuid) {
		this.commendWxuid = commendWxuid;
	}
}