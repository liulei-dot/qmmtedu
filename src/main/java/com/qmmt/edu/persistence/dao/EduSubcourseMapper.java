package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.EduSubcourse;

public interface EduSubcourseMapper {

	int deleteByPrimaryKey(Long id);

	int insert(EduSubcourse record);

	int insertSelective(EduSubcourse record);

	EduSubcourse selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(EduSubcourse record);

	int updateByPrimaryKey(EduSubcourse record);

	EduSubcourse selectOne(EduSubcourse record);

	List<EduSubcourse> selectList(EduSubcourse record);

	int selectCount(EduSubcourse record);

	int deleteSelective(EduSubcourse record);
}