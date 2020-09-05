package com.qmmt.edu.persistence.dao;

import java.util.List;

import com.qmmt.edu.persistence.po.AdminUser;

public interface AdminUserMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(AdminUser record);

	int insertSelective(AdminUser record);

	AdminUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AdminUser record);

	int updateByPrimaryKey(AdminUser record);

	AdminUser selectOne(AdminUser record);

	List<AdminUser> selectList(AdminUser record);

	int selectCount(AdminUser record);

	int deleteSelective(AdminUser record);
}