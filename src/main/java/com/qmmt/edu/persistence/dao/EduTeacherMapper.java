package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.EduTeacher;

public interface EduTeacherMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(EduTeacher record);

	int insertSelective(EduTeacher record);

	EduTeacher selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EduTeacher record);

	int updateByPrimaryKey(EduTeacher record);

	EduTeacher selectOne(EduTeacher record);

	List<EduTeacher> selectList(EduTeacher record);

	int selectCount(EduTeacher record);

	int deleteSelective(EduTeacher record);
}