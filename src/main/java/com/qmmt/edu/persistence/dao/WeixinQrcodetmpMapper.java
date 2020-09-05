package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinQrcodetmp;

public interface WeixinQrcodetmpMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(WeixinQrcodetmp record);

	int insertSelective(WeixinQrcodetmp record);

	WeixinQrcodetmp selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeixinQrcodetmp record);

	int updateByPrimaryKey(WeixinQrcodetmp record);

	WeixinQrcodetmp selectOne(WeixinQrcodetmp record);

	List<WeixinQrcodetmp> selectList(WeixinQrcodetmp record);

	int selectCount(WeixinQrcodetmp record);

	int deleteSelective(WeixinQrcodetmp record);
}