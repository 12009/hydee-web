package com.hydee.hdsec.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hydee.hdsec.entity.CompanyRole;
import com.hydee.hdsec.entity.CompanyUser;

@Repository
public interface CompanyRoleDao {
    int deleteByPrimaryKey(Long roleId);

    int insertSelective(CompanyRole record);

    CompanyRole selectByPrimaryKey(Long roleId);

    int updateByRoleId(CompanyRole record);

	List<CompanyRole> listByCreateId(long createId);

	int saveMenues(CompanyRole role);
	
	int clearMenuesForRoleId(long roleId);

	CompanyRole findByRoleName(CompanyRole role);

	int countUser(Long roleId);

	List<CompanyRole> listByCreateIdListPage(CompanyUser user);
	
}