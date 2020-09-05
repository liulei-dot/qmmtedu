package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.EduVideoroom;

public interface EduVideoroomMapper {

	int deleteByPrimaryKey(Long id);

	int insert(EduVideoroom record);

	int insertSelective(EduVideoroom record);

	EduVideoroom selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(EduVideoroom record);

	int updateByPrimaryKey(EduVideoroom record);

	EduVideoroom selectOne(EduVideoroom record);

	List<EduVideoroom> selectList(EduVideoroom record);

	int selectCount(EduVideoroom record);

	int deleteSelective(EduVideoroom record);
}