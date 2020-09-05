package com.qmmt.edu.persistence.po;

import java.util.Date;

import com.qmmt.edu.persistence.base.DBRecord;

public class EduVideoroom extends DBRecord {

	private Long id;

	private String roomId;

	private Long subcourseId;

	private String adminCode;

	private String teacherCode;

	private Date createTime;

	private Date modifyTime;

	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Long getSubcourseId() {
		return subcourseId;
	}

	public void setSubcourseId(Long subcourseId) {
		this.subcourseId = subcourseId;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}