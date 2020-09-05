package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.SysConfig;

public interface SysConfigMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(SysConfig record);

	int insertSelective(SysConfig record);

	SysConfig selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysConfig record);

	int updateByPrimaryKey(SysConfig record);

	SysConfig selectOne(SysConfig record);

	List<SysConfig> selectList(SysConfig record);

	int selectCount(SysConfig record);

	int deleteSelective(SysConfig record);
}