package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinMenuentity;

public interface WeixinMenuentityMapper {

	int deleteByPrimaryKey(String id);

	int insert(WeixinMenuentity record);

	int insertSelective(WeixinMenuentity record);

	WeixinMenuentity selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(WeixinMenuentity record);

	int updateByPrimaryKey(WeixinMenuentity record);

	WeixinMenuentity selectOne(WeixinMenuentity record);

	List<WeixinMenuentity> selectList(WeixinMenuentity record);

	int selectCount(WeixinMenuentity record);

	int deleteSelective(WeixinMenuentity record);
}