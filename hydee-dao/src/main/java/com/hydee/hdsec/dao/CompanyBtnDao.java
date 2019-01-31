package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.CompanyBtn;

public interface CompanyBtnDao {
    int deleteByPrimaryKey(Long btnId);

    int insert(CompanyBtn record);

    int insertSelective(CompanyBtn record);

    CompanyBtn selectByPrimaryKey(Long btnId);

    int updateByPrimaryKeySelective(CompanyBtn record);

    int updateByPrimaryKey(CompanyBtn record);
}