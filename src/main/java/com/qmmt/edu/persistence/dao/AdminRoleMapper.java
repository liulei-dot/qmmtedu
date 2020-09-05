package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.AdminRole;

public interface AdminRoleMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(AdminRole record);

	int insertSelective(AdminRole record);

	AdminRole selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AdminRole record);

	int updateByPrimaryKey(AdminRole record);

	AdminRole selectOne(AdminRole record);

	List<AdminRole> selectList(AdminRole record);

	int selectCount(AdminRole record);

	int deleteSelective(AdminRole record);
}