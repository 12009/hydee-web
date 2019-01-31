package com.hydee.hdsec.dao;


import java.util.List;
import java.util.Map;

import com.hydee.hdsec.entity.CompanyUser;

public interface CompanyUserDao {
    int deleteCompanyUserById(Long id);

    int insertCompanyUser(CompanyUser companyUser);

    CompanyUser selectByArgs(CompanyUser companyUser);

    int updateByCompanyUser(CompanyUser companyUser);

    CompanyUser selectUserByPassWord(CompanyUser companyUser);

	String findUsefulVerifiCodeByUsername(String userName);

	List<CompanyUser> listByCreaterIdListPage(CompanyUser user);

	CompanyUser selectById(Long userId);

	CompanyUser findAdminByCustomerId(String customerId);

    CompanyUser selectUserByParams(Map<String,Object> map);
}