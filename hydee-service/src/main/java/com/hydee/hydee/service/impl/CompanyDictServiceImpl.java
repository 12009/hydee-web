package com.hydee.hydee.service.impl;

import com.hydee.hdsec.dao.CompanyDictDao;
import com.hydee.hdsec.entity.CompanyDict;
import com.hydee.hydee.service.CompanyDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyDictServiceImpl implements CompanyDictService {
	
	@Autowired
	CompanyDictDao companyDictDao;


	@Override
	public List<CompanyDict> selectByType(String dictType) throws Exception {
		return companyDictDao.selectByType(dictType);
	}
}
