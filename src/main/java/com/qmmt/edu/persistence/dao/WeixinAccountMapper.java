package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinAccount;

public interface WeixinAccountMapper {

	int deleteByPrimaryKey(String id);

	int insert(WeixinAccount record);

	int insertSelective(WeixinAccount record);

	WeixinAccount selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(WeixinAccount record);

	int updateByPrimaryKey(WeixinAccount record);

	WeixinAccount selectOne(WeixinAccount record);

	List<WeixinAccount> selectList(WeixinAccount record);

	int selectCount(WeixinAccount record);

	int deleteSelective(WeixinAccount record);
}