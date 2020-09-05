package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.AdminMenu;

public interface AdminMenuMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(AdminMenu record);

	int insertSelective(AdminMenu record);

	AdminMenu selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AdminMenu record);

	int updateByPrimaryKey(AdminMenu record);

	AdminMenu selectOne(AdminMenu record);

	List<AdminMenu> selectList(AdminMenu record);

	int selectCount(AdminMenu record);

	int deleteSelective(AdminMenu record);
}