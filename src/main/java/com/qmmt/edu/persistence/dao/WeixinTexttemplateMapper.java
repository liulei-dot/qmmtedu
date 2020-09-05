package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinTexttemplate;

public interface WeixinTexttemplateMapper {

	int deleteByPrimaryKey(String id);

	int insert(WeixinTexttemplate record);

	int insertSelective(WeixinTexttemplate record);

	WeixinTexttemplate selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(WeixinTexttemplate record);

	int updateByPrimaryKey(WeixinTexttemplate record);

	WeixinTexttemplate selectOne(WeixinTexttemplate record);

	List<WeixinTexttemplate> selectList(WeixinTexttemplate record);

	int selectCount(WeixinTexttemplate record);

	int deleteSelective(WeixinTexttemplate record);
}