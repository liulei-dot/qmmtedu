package com.qmmt.edu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qmmt.edu.persistence.dao.EduTeacherMapper;
import com.qmmt.edu.persistence.po.EduTeacher;

@Service("eduTeacherService")
public class EduTeacherService {

	@Autowired
	EduTeacherMapper eduTeacherMapper;

	
	public List<EduTeacher> getEduTeacherList() {
		EduTeacher record = new EduTeacher();
		List<EduTeacher> retList = eduTeacherMapper.selectList(record);
		return retList;
	}
	
	public EduTeacher getEduTeacher(Integer id) {
		EduTeacher record = eduTeacherMapper.selectByPrimaryKey(id);
		return record;
	}
	
	public boolean addEduTeacher(String techerName,String headerUrl,String info) {
		EduTeacher record = new EduTeacher();
		record.setTecherName(techerName);
		record.setHeaderUrl(headerUrl);
		record.setInfo(info);
		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		int res = eduTeacherMapper.insertSelective(record);
		return true;
	}
	
	public boolean updateEduTeacher(Integer id,String techerName,String headerUrl,String info) {
		EduTeacher record = new EduTeacher();
		record.setId(id);
		record.setTecherName(techerName);
		record.setHeaderUrl(headerUrl);
		record.setInfo(info);
		record.setModifyTime(new Date());
		int res = eduTeacherMapper.updateByPrimaryKeySelective(record);
		return true;
	}

}
