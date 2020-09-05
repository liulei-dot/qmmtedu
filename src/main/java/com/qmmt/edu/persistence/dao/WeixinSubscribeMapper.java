package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinSubscribe;

public interface WeixinSubscribeMapper {

	int deleteByPrimaryKey(String id);

	int insert(WeixinSubscribe record);

	int insertSelective(WeixinSubscribe record);

	WeixinSubscribe selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(WeixinSubscribe record);

	int updateByPrimaryKey(WeixinSubscribe record);

	WeixinSubscribe selectOne(WeixinSubscribe record);

	List<WeixinSubscribe> selectList(WeixinSubscribe record);

	int selectCount(WeixinSubscribe record);

	int deleteSelective(WeixinSubscribe record);
}