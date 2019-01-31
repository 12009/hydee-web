package com.hydee.hydee.service;

import com.hydee.hdsec.entity.CompanyDict;
import com.hydee.hdsec.entity.CompanyRole;
import com.hydee.hdsec.entity.CompanyUser;

import java.util.List;

public interface CompanyDictService {

	List<CompanyDict> selectByType(String dictType) throws Exception;
	
}
