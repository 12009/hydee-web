package com.hydee.hdsec.dao;


import com.hydee.hdsec.entity.CompanyOrganization;

public interface CompanyOrganizationDao {
    int deleteByPrimaryKey(String customerId);

    int insertSelective(CompanyOrganization record);

    CompanyOrganization selectByPrimaryKey(String customerId);

    int updateByPrimaryKeySelective(CompanyOrganization record);
    
    CompanyOrganization selectByOrgname(CompanyOrganization companyOrganization);
    
}