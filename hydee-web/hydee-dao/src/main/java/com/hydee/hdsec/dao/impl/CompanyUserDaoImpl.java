package com.hydee.hdsec.dao.impl;

import java.util.List;
import java.util.Map;

import com.hydee.hdsec.dao.CompanyUserDao;
import com.hydee.hdsec.dao.base.BaseDao;
import com.hydee.hdsec.entity.CompanyUser;

import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/8/16.
 */
@Repository("companyUserDao")
public class CompanyUserDaoImpl extends BaseDao implements CompanyUserDao {
    @Override
    public int deleteCompanyUserById(Long id) {
        return (Integer) delete("companyUserMapper.deleteCompanyUserById", id);
    }

    @Override
    public int insertCompanyUser(CompanyUser record) {
        return (Integer) save("companyUserMapper.insertCompanyUser", record);
    }

    @Override
    public CompanyUser selectByArgs(CompanyUser companyUser) {
        return (CompanyUser) findForObject("companyUserMapper.selectByArgs", companyUser);
    }

    @Override
    public int updateByCompanyUser(CompanyUser companyUser) {
        return (Integer) update("companyUserMapper.updateByCompanyUser", companyUser);
    }

    @Override
    public CompanyUser selectUserByPassWord(CompanyUser companyUser) {
        return (CompanyUser) findForObject("companyUserMapper.selectUserByPassWord",companyUser);
    }

	@Override
	public String findUsefulVerifiCodeByUsername(String userName) {
		return (String) findForObject("companyUserMapper.findUsefulVerifiCodeByUsername", userName);
	}

	@Override
	public List<CompanyUser> listByCreaterIdListPage(CompanyUser user) {
		return (List<CompanyUser>) findForList("companyUserMapper.listByCreaterIdListPage", user);
	}

	@Override
	public CompanyUser selectById(Long userId) {
		return (CompanyUser) findForObject("companyUserMapper.selectById",userId);
	}

	@Override
	public CompanyUser findAdminByCustomerId(String customerId) {
		return (CompanyUser) findForObject("companyUserMapper.findAdminByCustomerId",customerId);
	}

    @Override
    public CompanyUser selectUserByParams(Map<String, Object> map) {
        return (CompanyUser) findForObject("companyUserMapper.selectUserByParams",map);
    }
}
