package com.hydee.hdsec.service.impl;

import com.hydee.hdsec.sqlDao.CompanyDao;
import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyImpl implements CompanyService {
	
	@Autowired
	private CompanyDao companyDao;


	@Override
	public List<Company> listCompany() {
		return companyDao.listCompany();
	}

	@Override
	public Company getCompanyByCustomerId(String xmCustomerId) {
		return companyDao.getCompanyByCustomerId(xmCustomerId);
	}
}
