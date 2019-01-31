package com.hydee.hdsec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.CompanyUserAccountDao;
import com.hydee.hdsec.entity.CompanyUserAccount;
import com.hydee.hdsec.service.CompanyUserAccountService;
import com.hydee.hdsec.util.StringUtil;

/**
 * 用户 业务层实现类
 * Created by Administrator on 2016/8/16.
 */
@Service
public class CompanyUserAccountServiceImpl implements CompanyUserAccountService {
	@Autowired
    CompanyUserAccountDao companyUserAccountDao;


	@Override
	public int insertUserAccount(CompanyUserAccount userAccount) throws Exception {
		return companyUserAccountDao.insertUserAccount(userAccount);
	}


	@Override
	public double selectUserBalance(String customerId, String userId) throws ServiceException {
		if(StringUtil.isBlanks(false, customerId, userId)){
			throw new ServiceException(ErrorCodes.UID_CHCID_IS_NULL);
		}
		CompanyUserAccount account = new CompanyUserAccount();
		account.setUserId(userId);
		account.setCustomerId(customerId);
		account = companyUserAccountDao.selectUserAccountByUidAndCid(account);
		return (account == null || account.getBalance() == null) ? 0 : account.getBalance();
	}


	@Override
	public int takeoutBalance(CompanyUserAccount userAccount) throws ServiceException {
		if(StringUtil.isBlanks(false, userAccount.getCustomerId(), userAccount.getUserId())){
			throw new ServiceException(ErrorCodes.UID_CHCID_IS_NULL);
		}
		return companyUserAccountDao.takeoutBalance(userAccount);
	}


	@Override
	public int takeoutBalanceBatch(List<CompanyUserAccount> userAccountList) {
		return companyUserAccountDao.takeoutBalanceBatch(userAccountList);
	}
}
