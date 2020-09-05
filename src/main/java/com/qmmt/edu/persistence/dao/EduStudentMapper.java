package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.EduStudent;

public interface EduStudentMapper {

	int deleteByPrimaryKey(Long id);

	int insert(EduStudent record);

	int insertSelective(EduStudent record);

	EduStudent selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(EduStudent record);

	int updateByPrimaryKey(EduStudent record);

	EduStudent selectOne(EduStudent record);

	List<EduStudent> selectList(EduStudent record);

	int selectCount(EduStudent record);

	int deleteSelective(EduStudent record);
}