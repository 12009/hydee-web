package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.CompanyDict;
import com.hydee.hdsec.entity.CompanyUserAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDictDao {

	List<CompanyDict> selectByType(String dictType);
}
