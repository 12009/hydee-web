package com.hydee.hdsec.service;

import com.hydee.hdsec.entity.*;

import java.util.List;

public interface CompanyService {

	List<Company> listCompany();

	public Company getCompanyByCustomerId(String xmCustomerId);
}
