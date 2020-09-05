package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinAutoresponse;

public interface WeixinAutoresponseMapper {

	int deleteByPrimaryKey(String id);

	int insert(WeixinAutoresponse record);

	int insertSelective(WeixinAutoresponse record);

	WeixinAutoresponse selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(WeixinAutoresponse record);

	int updateByPrimaryKey(WeixinAutoresponse record);

	WeixinAutoresponse selectOne(WeixinAutoresponse record);

	List<WeixinAutoresponse> selectList(WeixinAutoresponse record);

	int selectCount(WeixinAutoresponse record);

	int deleteSelective(WeixinAutoresponse record);
}