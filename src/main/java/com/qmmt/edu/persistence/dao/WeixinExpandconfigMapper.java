package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinExpandconfig;

public interface WeixinExpandconfigMapper {

	int deleteByPrimaryKey(String id);

	int insert(WeixinExpandconfig record);

	int insertSelective(WeixinExpandconfig record);

	WeixinExpandconfig selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(WeixinExpandconfig record);

	int updateByPrimaryKeyWithBLOBs(WeixinExpandconfig record);

	int updateByPrimaryKey(WeixinExpandconfig record);

	WeixinExpandconfig selectOne(WeixinExpandconfig record);

	List<WeixinExpandconfig> selectList(WeixinExpandconfig record);

	int selectCount(WeixinExpandconfig record);

	int deleteSelective(WeixinExpandconfig record);
}