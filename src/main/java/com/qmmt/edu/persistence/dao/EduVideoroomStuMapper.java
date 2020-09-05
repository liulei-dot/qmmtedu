package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.EduVideoroomStu;

public interface EduVideoroomStuMapper {

	int deleteByPrimaryKey(Long id);

	int insert(EduVideoroomStu record);

	int insertSelective(EduVideoroomStu record);

	EduVideoroomStu selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(EduVideoroomStu record);

	int updateByPrimaryKey(EduVideoroomStu record);

	EduVideoroomStu selectOne(EduVideoroomStu record);

	List<EduVideoroomStu> selectList(EduVideoroomStu record);

	int selectCount(EduVideoroomStu record);

	int deleteSelective(EduVideoroomStu record);
}