package com.qmmt.edu.persistence.po;

import java.util.Date;

import com.qmmt.edu.persistence.base.DBRecord;

public class WeixinQrcodetmp extends DBRecord {

	private Integer id;

	private Integer qcode;

	private Integer qtype;

	private String ticket;

	private Long expireSeconds;

	private String url;

	private Date createTime;

	private String accountid;

	private Integer status;

	private String purpose;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQcode() {
		return qcode;
	}

	public void setQcode(Integer qcode) {
		this.qcode = qcode;
	}

	public Integer getQtype() {
		return qtype;
	}

	public void setQtype(Integer qtype) {
		this.qtype = qtype;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Long getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(Long expireSeconds) {
		this.expireSeconds = expireSeconds;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
}