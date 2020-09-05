package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinNewstemplate;

public interface WeixinNewstemplateMapper {

	int deleteByPrimaryKey(String id);

	int insert(WeixinNewstemplate record);

	int insertSelective(WeixinNewstemplate record);

	WeixinNewstemplate selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(WeixinNewstemplate record);

	int updateByPrimaryKey(WeixinNewstemplate record);

	WeixinNewstemplate selectOne(WeixinNewstemplate record);

	List<WeixinNewstemplate> selectList(WeixinNewstemplate record);

	int selectCount(WeixinNewstemplate record);

	int deleteSelective(WeixinNewstemplate record);
}