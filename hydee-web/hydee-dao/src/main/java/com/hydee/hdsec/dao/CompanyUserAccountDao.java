package com.hydee.hdsec.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hydee.hdsec.entity.CompanyUserAccount;

@Repository
public interface CompanyUserAccountDao {

	int insertUserAccount(CompanyUserAccount userAccount);

	int updateUserAccount(CompanyUserAccount userAccount);

	CompanyUserAccount selectUserAccountByUidAndCid(CompanyUserAccount account);

	int takeoutBalance(CompanyUserAccount userAccount);

	int takeoutBalanceBatch(List<CompanyUserAccount> userAccountList);
}
