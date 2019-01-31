package com.hydee.hdsec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.CompanyRoleDao;
import com.hydee.hdsec.entity.CompanyRole;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hdsec.service.CompanyRoleService;

@Service
public class CompanyRoleServiceImpl implements CompanyRoleService {
	
	@Autowired
	CompanyRoleDao roleDao;
	
	@Override
	public List<CompanyRole> listByCreateId(long createId) throws Exception {
		return roleDao.listByCreateId(createId);
	}

	@Override
	public int saveOrUpdate(CompanyRole role) throws Exception {
		int count = 0;
		if(role.getRoleId() == null){
			if(roleDao.findByRoleName(role) != null){
				throw new ServiceException(ErrorCodes.ROLENAME_HAS_REPEATED);
			}
			count = roleDao.insertSelective(role);
		}else{
			count = roleDao.updateByRoleId(role);
		}
		return count;
	}

	@Override
	public int saveMenues(CompanyRole role) throws Exception {
		roleDao.clearMenuesForRoleId(role.getRoleId());
		return roleDao.saveMenues(role);
	}

	@Override
	public int deleteByRoleId(Long roleId) throws Exception {
		roleDao.clearMenuesForRoleId(roleId);
		return roleDao.deleteByPrimaryKey(roleId);
	}

	@Override
	public int countUser(Long roleId) throws Exception {
		return roleDao.countUser(roleId);
	}

	@Override
	public List<CompanyRole> listByCreateIdListPage(CompanyUser user) {
		return roleDao.listByCreateIdListPage(user);
	}

}
