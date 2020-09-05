package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.EduCourse;

public interface EduCourseMapper {

	int deleteByPrimaryKey(Long id);

	int insert(EduCourse record);

	int insertSelective(EduCourse record);

	EduCourse selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(EduCourse record);

	int updateByPrimaryKey(EduCourse record);

	EduCourse selectOne(EduCourse record);

	List<EduCourse> selectList(EduCourse record);

	int selectCount(EduCourse record);

	int deleteSelective(EduCourse record);
}