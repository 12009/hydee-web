package com.hydee.hdsec.service;

import java.util.List;
import java.util.Map;

import com.hydee.hdsec.entity.CompanyUser;


/**
 * Created by Administrator on 2016/8/16.
 */
public interface CompanyUserService {

    CompanyUser selectUserByPassWord(CompanyUser companyUser) throws Exception;

    int updateByCompanyUser(CompanyUser companyUser) throws Exception;

	CompanyUser findUserByUsername(CompanyUser companyUser) throws Exception;

	int registUser(CompanyUser companyUser) throws Exception;

	CompanyUser findeUserByUsernameAndEmail(String userName, String email) throws Exception;

	int updateVerifyCode(Long id, String verifiCode) throws Exception;

	String getUsefulVerifiCode(String userName) throws Exception;

	List<CompanyUser> listByCreaterIdListPage(CompanyUser user) throws Exception;

	CompanyUser findUserById(Long userId) throws Exception;

	int saveOrUpdate(CompanyUser user) throws Exception;

	CompanyUser findAdminByCustomerId(String customerId) throws Exception;

	public CompanyUser selectUserByParams(Map<String, Object> map) throws Exception;
}
