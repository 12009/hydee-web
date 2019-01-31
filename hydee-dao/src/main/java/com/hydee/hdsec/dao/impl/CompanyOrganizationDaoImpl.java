package com.hydee.hdsec.dao.impl;


import org.springframework.stereotype.Repository;

import com.hydee.hdsec.dao.CompanyOrganizationDao;
import com.hydee.hdsec.dao.base.BaseDao;
import com.hydee.hdsec.entity.CompanyOrganization;

import java.util.List;

@Repository("companyOrganizationDao")
public class CompanyOrganizationDaoImpl extends BaseDao implements CompanyOrganizationDao {

	
	@Override
	public int deleteByPrimaryKey(String customerId) {
		// TODO Auto-generated method stub
		return (Integer) delete("com.hydee.hdsec.dao.CompanyOrganizationDao.deleteByPrimaryKey",customerId);
	}
	 

	@Override
	public int insertSelective(CompanyOrganization record) {
		// TODO Auto-generated method stub
		return (Integer) save("com.hydee.hdsec.dao.CompanyOrganizationDao.insertSelective",record);
	}

	@Override
	public CompanyOrganization selectByPrimaryKey(String customerId) {
		// TODO Auto-generated method stub
		return (CompanyOrganization) findForObject("com.hydee.hdsec.dao.CompanyOrganizationDao.selectByPrimaryKey",customerId);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyOrganization record) {
		// TODO Auto-generated method stub
		return (Integer) update("com.hydee.hdsec.dao.CompanyOrganizationDao.updateByPrimaryKeySelective",record);
	}


	@Override
	public CompanyOrganization selectByOrgname(
			CompanyOrganization companyOrganization) {
		return (CompanyOrganization) findForObject("com.hydee.hdsec.dao.CompanyOrganizationDao.selectByOrgname",companyOrganization);
	}

	@Override
	public List<CompanyOrganization> selectOrganization() {
		return (List<CompanyOrganization>) findForList("com.hydee.hdsec.dao.CompanyOrganizationDao.selectOrganization");
	}


}
