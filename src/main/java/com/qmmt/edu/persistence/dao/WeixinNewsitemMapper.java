package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinNewsitem;

public interface WeixinNewsitemMapper {

	int deleteByPrimaryKey(String id);

	int insert(WeixinNewsitem record);

	int insertSelective(WeixinNewsitem record);

	WeixinNewsitem selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(WeixinNewsitem record);

	int updateByPrimaryKeyWithBLOBs(WeixinNewsitem record);

	int updateByPrimaryKey(WeixinNewsitem record);

	WeixinNewsitem selectOne(WeixinNewsitem record);

	List<WeixinNewsitem> selectList(WeixinNewsitem record);

	int selectCount(WeixinNewsitem record);

	int deleteSelective(WeixinNewsitem record);
}