package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.WeixinQrcode;

public interface WeixinQrcodeMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(WeixinQrcode record);

	int insertSelective(WeixinQrcode record);

	WeixinQrcode selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeixinQrcode record);

	int updateByPrimaryKey(WeixinQrcode record);

	WeixinQrcode selectOne(WeixinQrcode record);

	List<WeixinQrcode> selectList(WeixinQrcode record);

	int selectCount(WeixinQrcode record);

	int deleteSelective(WeixinQrcode record);
}