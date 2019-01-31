package com.hydee.hdsec.dao;

import java.util.List;

import com.hydee.hdsec.entity.CompanyAccountRecharge;
import com.hydee.hdsec.entity.Page;
  

public interface CompanyAccountRechargeDao {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyAccountRecharge record);

    int insertSelective(CompanyAccountRecharge record);

    /**
     * 查询可用的新流水号
     * */
    String getSerialNumber();
    
    CompanyAccountRecharge selectByPrimaryKey(Long id);
    
    CompanyAccountRecharge selectBySerialNumber(String serialNumber);
    
    List<CompanyAccountRecharge> selectListPage(CompanyAccountRecharge companyAccountRecharge);
    
    int updateByPrimaryKeySelective(CompanyAccountRecharge record);

    int updateByPrimaryKey(CompanyAccountRecharge record);
    
    int updateBySerialNumberSelective(CompanyAccountRecharge record);
    
}