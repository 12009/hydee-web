package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.CompanyAccount;

import java.util.List;
import java.util.Map;

public interface CompanyAccountDao {

	int deleteByPrimaryKey(Long id);

    int insert(CompanyAccount record);

    int insertSelective(CompanyAccount record);

    CompanyAccount selectByPrimaryKey(Long id);
    
    CompanyAccount selectByCustomerId(String id);

    int updateByPrimaryKeySelective(CompanyAccount record);

    int updateByPrimaryKey(CompanyAccount record);

    List<String> queryMonthTaskByCustomer(Map<String,Object> map);

    int getTaskDoingAccount(String customerId);
}
