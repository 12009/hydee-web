package com.hydee.hdsec.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hydee.hdsec.dao.CompanyOrganizationDao;
import com.hydee.hdsec.entity.CompanyOrganization;
import com.hydee.hdsec.service.CompanyOrgService;
import com.hydee.hdsec.util.StringUtil;

@Service
public class CompanyOrgServiceImpl implements CompanyOrgService {
	
	@Autowired
	CompanyOrganizationDao orgDao;

	@Override
	public int saveOrUpdate(CompanyOrganization org) throws Exception {
		if(StringUtil.isBlank(org.getCustomerId())){
			org.setCustomerId(UUID.randomUUID().toString().replace("-", ""));
			return orgDao.insertSelective(org);
		}else{
			return orgDao.updateByPrimaryKeySelective(org);
		}
	}

	@Override
	public CompanyOrganization findOrgByOrgname(CompanyOrganization org) throws Exception {
		return orgDao.selectByOrgname(org);
	}

	@Override
	public CompanyOrganization findByCustomerId(String customerId) throws Exception {
		return orgDao.selectByPrimaryKey(customerId);
	}

}
