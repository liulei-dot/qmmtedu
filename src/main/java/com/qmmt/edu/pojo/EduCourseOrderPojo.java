package com.qmmt.edu.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.qmmt.edu.persistence.po.EduTeacher;

public class EduCourseOrderPojo {
	
	private Long id;

	private Long wxUid;

	private BigDecimal receivableAmount;

	private BigDecimal realpayAmount;

	private Date payTime;

	private Integer payType;
	
	private Long courseId;

	private String courseName;

	private Integer teacherId;
	
	private EduTeacher eduTeacher;

	private Date openTime;

	private Date closeTime;

	private String ctype;

	private BigDecimal courseFee;

	private String imgUrl;
	
	private String roomId;

	private String info;

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

	public BigDecimal getReceivableAmount() {
		return receivableAmount;
	}

	public void setReceivableAmount(BigDecimal receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	public BigDecimal getRealpayAmount() {
		return realpayAmount;
	}

	public void setRealpayAmount(BigDecimal realpayAmount) {
		this.realpayAmount = realpayAmount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	

}
