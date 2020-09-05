package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.AdminRoleMenuMapping;

public interface AdminRoleMenuMappingMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(AdminRoleMenuMapping record);

	int insertSelective(AdminRoleMenuMapping record);

	AdminRoleMenuMapping selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AdminRoleMenuMapping record);

	int updateByPrimaryKey(AdminRoleMenuMapping record);

	AdminRoleMenuMapping selectOne(AdminRoleMenuMapping record);

	List<AdminRoleMenuMapping> selectList(AdminRoleMenuMapping record);

	int selectCount(AdminRoleMenuMapping record);

	int deleteSelective(AdminRoleMenuMapping record);
}