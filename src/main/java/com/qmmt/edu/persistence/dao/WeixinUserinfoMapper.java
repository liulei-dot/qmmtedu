package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinUserinfo;

public interface WeixinUserinfoMapper {

	int deleteByPrimaryKey(Long id);

	int insert(WeixinUserinfo record);

	int insertSelective(WeixinUserinfo record);

	WeixinUserinfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(WeixinUserinfo record);

	int updateByPrimaryKey(WeixinUserinfo record);

	WeixinUserinfo selectOne(WeixinUserinfo record);

	List<WeixinUserinfo> selectList(WeixinUserinfo record);

	int selectCount(WeixinUserinfo record);

	int deleteSelective(WeixinUserinfo record);
}