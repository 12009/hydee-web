package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.CompanyRoleresop;

public interface CompanyRoleresopDao {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyRoleresop record);

    int insertSelective(CompanyRoleresop record);

    CompanyRoleresop selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyRoleresop record);

    int updateByPrimaryKey(CompanyRoleresop record);
}