package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinReceivetext;

public interface WeixinReceivetextMapper {

	int deleteByPrimaryKey(Long id);

	int insert(WeixinReceivetext record);

	int insertSelective(WeixinReceivetext record);

	WeixinReceivetext selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(WeixinReceivetext record);

	int updateByPrimaryKey(WeixinReceivetext record);

	WeixinReceivetext selectOne(WeixinReceivetext record);

	List<WeixinReceivetext> selectList(WeixinReceivetext record);

	int selectCount(WeixinReceivetext record);

	int deleteSelective(WeixinReceivetext record);
}