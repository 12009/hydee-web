package com.hydee.hdsec.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hydee.hdsec.entity.CompanyAccountWhere;

@Repository
public interface CompanyAccountWhereDao {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyAccountWhere record);

    int insertSelective(CompanyAccountWhere record);

    CompanyAccountWhere selectByPrimaryKey(Long id);
    
    String getSerialNumber();
    
    List<CompanyAccountWhere> queryAccountWhereListPage(CompanyAccountWhere record);
    
    int updateByPrimaryKeySelective(CompanyAccountWhere record);
    
    int updateByPrimaryKey(CompanyAccountWhere record);

	Double countRedMoney(CompanyAccountWhere accountWhere);

	double getSumTaskCharge(String customerId);
}