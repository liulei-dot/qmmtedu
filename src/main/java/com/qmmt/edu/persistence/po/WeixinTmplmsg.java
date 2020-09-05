package com.qmmt.edu.persistence.po;

import com.qmmt.edu.persistence.base.DBRecord;

public class WeixinTmplmsg extends DBRecord {

	private Integer id;

	private String tmplKeyword;

	private String tmplName;

	private String wxtmplCode;

	private String weixinAccountid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTmplKeyword() {
		return tmplKeyword;
	}

	public void setTmplKeyword(String tmplKeyword) {
		this.tmplKeyword = tmplKeyword;
	}

	public String getTmplName() {
		return tmplName;
	}

	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}

	public String getWxtmplCode() {
		return wxtmplCode;
	}

	public void setWxtmplCode(String wxtmplCode) {
		this.wxtmplCode = wxtmplCode;
	}

	public String getWeixinAccountid() {
		return weixinAccountid;
	}

	public void setWeixinAccountid(String weixinAccountid) {
		this.weixinAccountid = weixinAccountid;
	}
}