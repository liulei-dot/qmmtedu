package com.qmmt.edu.persistence.po;

import java.util.Date;

import com.qmmt.edu.persistence.base.DBRecord;

public class EduVideoroomStu extends DBRecord {

	private Long id;

	private String roomId;

	private Long subcourseId;

	private Long wxUid;

	private String studentCode;

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

	public Long getWxUid() {
		return wxUid;
	}

	public void setWxUid(Long wxUid) {
		this.wxUid = wxUid;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
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