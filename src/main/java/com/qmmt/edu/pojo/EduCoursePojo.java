package com.qmmt.edu.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.qmmt.edu.persistence.po.EduTeacher;

public class EduCoursePojo {
	
	private Long id;

	private String courseName;

	private Integer teacherId;
	
	private EduTeacher eduTeacher;
	
	private Integer courseNum;

	private Date openTime;

	private Date closeTime;
	
	private Integer orderNum;

	private String ctype;

	private BigDecimal courseFee;
	
	private BigDecimal marketCourseFee;

	private String imgUrl;
	
	private Integer courseType;
	
	private String priceInfo;

	private String timeInfo;

	private String remark;

	private String info;
	
	private List<EduSubcoursePojo> eduSubcourseList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public EduTeacher getEduTeacher() {
		return eduTeacher;
	}

	public void setEduTeacher(EduTeacher eduTeacher) {
		this.eduTeacher = eduTeacher;
	}

	public Integer getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public BigDecimal getCourseFee() {
		return courseFee;
	}

	public void setCourseFee(BigDecimal courseFee) {
		this.courseFee = courseFee;
	}

	public BigDecimal getMarketCourseFee() {
		return marketCourseFee;
	}

	public void setMarketCourseFee(BigDecimal marketCourseFee) {
		this.marketCourseFee = marketCourseFee;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPriceInfo() {
		return priceInfo;
	}

	public void setPriceInfo(String priceInfo) {
		this.priceInfo = priceInfo;
	}

	public String getTimeInfo() {
		return timeInfo;
	}

	public void setTimeInfo(String timeInfo) {
		this.timeInfo = timeInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	public List<EduSubcoursePojo> getEduSubcourseList() {
		return eduSubcourseList;
	}

	public void setEduSubcourseList(List<EduSubcoursePojo> eduSubcourseList) {
		this.eduSubcourseList = eduSubcourseList;
	}
	
	

}
