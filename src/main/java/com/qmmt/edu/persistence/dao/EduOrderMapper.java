package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.EduOrder;

public interface EduOrderMapper {

	int deleteByPrimaryKey(Long id);

	int insert(EduOrder record);

	int insertSelective(EduOrder record);

	EduOrder selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(EduOrder record);

	int updateByPrimaryKey(EduOrder record);

	EduOrder selectOne(EduOrder record);

	List<EduOrder> selectList(EduOrder record);

	int selectCount(EduOrder record);

	int deleteSelective(EduOrder record);
}