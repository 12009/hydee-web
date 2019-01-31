package com.hydee.hdsec.service;

import java.util.List;

import com.hydee.hdsec.entity.CompanyRole;
import com.hydee.hdsec.entity.CompanyUser;

public interface CompanyRoleService {
	
	List<CompanyRole> listByCreateId(long createId) throws Exception;

	int saveOrUpdate(CompanyRole role) throws Exception;

	int saveMenues(CompanyRole role) throws Exception;

	int deleteByRoleId(Long roleId) throws Exception;

	int countUser(Long roleId) throws Exception;

	List<CompanyRole> listByCreateIdListPage(CompanyUser user);
	
}
