package com.hydee.hdsec.dao;


import java.util.List;

import com.hydee.hdsec.entity.CompanyMenu;

public interface CompanyMenuDao {
    int deleteByPrimaryKey(Long menuId);

    int insert(CompanyMenu companyMenu);

    CompanyMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(CompanyMenu companyMenu);

    int updateByPrimaryKey(CompanyMenu companyMenu);

	List<CompanyMenu> listByRoleId(long roleId);
}