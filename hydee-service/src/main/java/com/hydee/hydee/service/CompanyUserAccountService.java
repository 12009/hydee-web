package com.hydee.hydee.service;

import java.util.List;

import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.CompanyUserAccount;

/**
 * Created by Administrator on 2016/8/16.
 */
public interface CompanyUserAccountService {
	/**
	 * 插入一条用户余额记录
	 * @param userAccount
	 * @return
	 * @throws Exception
	 */
    int insertUserAccount(CompanyUserAccount userAccount) throws Exception;
    /**
     * 查询
     * @param customerId
     * @param userId
     * @return
     * @throws ServiceException
     */
	double selectUserBalance(String customerId, String userId) throws ServiceException;
	/**
	 * 扣除可用余额
	 * @param userAccount
	 * @return
	 * @throws ServiceException
	 */
	int takeoutBalance(CompanyUserAccount userAccount) throws ServiceException;
	/**
	 * 批量扣除可用余额
	 * @param userAccountList
	 * @return
	 */
	int takeoutBalanceBatch(List<CompanyUserAccount> userAccountList);

	/**
	 * 钱包里面插入钱
	 * @param companyUserAccount
	 * @return
	 * @throws ServiceException
	 */
	int addSecWallet(CompanyUserAccount companyUserAccount) throws ServiceException;
}
