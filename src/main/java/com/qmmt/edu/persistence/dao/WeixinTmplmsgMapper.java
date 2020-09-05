package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinTmplmsg;

public interface WeixinTmplmsgMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(WeixinTmplmsg record);

	int insertSelective(WeixinTmplmsg record);

	WeixinTmplmsg selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeixinTmplmsg record);

	int updateByPrimaryKey(WeixinTmplmsg record);

	WeixinTmplmsg selectOne(WeixinTmplmsg record);

	List<WeixinTmplmsg> selectList(WeixinTmplmsg record);

	int selectCount(WeixinTmplmsg record);

	int deleteSelective(WeixinTmplmsg record);
}