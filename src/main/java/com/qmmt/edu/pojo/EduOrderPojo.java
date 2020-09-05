package com.qmmt.edu.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class EduOrderPojo {

	private Long id;

	private Long studentId;

	private Long wxUid;

	private Long courseId;
	
	private String courseName;
	
	private String imgUrl;

	private BigDecimal receivableAmount;

	private Date payTime;

	private String openId;

	private String wxaccount;
	
	private BigDecimal realpayAmount;

	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getWxUid() {
		return wxUid;
	}

	public void setWxUid(Long wxUid) {
		this.wxUid = wxUid;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public BigDecimal getReceivableAmount() {
		return receivableAmount;
	}

	public void setReceivableAmount(BigDecimal receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWxaccount() {
		return wxaccount;
	}

	public void setWxaccount(String wxaccount) {
		this.wxaccount = wxaccount;
	}

	public BigDecimal getRealpayAmount() {
		return realpayAmount;
	}

	public void setRealpayAmount(BigDecimal realpayAmount) {
		this.realpayAmount = realpayAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}