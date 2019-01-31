package com.hydee.hydee.service;

import java.util.List;

import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.CompanyWithdrawal;
import com.hydee.hdsec.vo.JsonVo;

public interface CompanyWithdrawalService {
	/**
	 * 新增提现记录
	 * @param withdrawal
	 * @return
	 * @throws ServiceException
	 */
	int insertWithdrawal(CompanyWithdrawal withdrawal) throws ServiceException;
	/**
	 * 查询提现列表(App用)
	 * @param withdrawal
	 * @return
	 * @throws ServiceException
	 */
	List<CompanyWithdrawal> queryWithdraws(CompanyWithdrawal withdrawal) throws ServiceException;
	/**
	 * 查询提现列表(小蜜超管大款用)
	 * @param withdrawal
	 * @return
	 * @throws ServiceException
	 */
	List<CompanyWithdrawal> queryAllWithdraws(CompanyWithdrawal withdrawal) throws ServiceException;
	/**
	 * 删除提现记录
	 * @param withdrawal
	 * @return
	 * @throws ServiceException
	 */
	int deleteWithdrawal(CompanyWithdrawal withdrawal) throws ServiceException;
	/**
	 * 查询用户近日提现次数
	 * @param chainCustomerId
	 * @param userId
	 * @return
	 */
	int selectTodayWithdrawCount(String customerId, String userId) throws ServiceException;
	/**
	 * 查询用户近日提现次数
	 * @param chainCustomerId
	 * @param userId
	 * @return
	 */
	int selectTodayWithdrawCount(CompanyWithdrawal withdrawal) throws ServiceException;
	/**
	 * 按照非空参数更新字段
	 * @param withdrawal
	 * @return
	 */
	int updateSelective(CompanyWithdrawal withdrawal) throws ServiceException;
	/**
	 * 批量更新非空参数字段
	 * @param withdrawalList
	 * @return
	 */
	int updateSelectiveBatch(List<CompanyWithdrawal> withdrawalList);
	/**
	 * 批量更新提现管理状态
	 * @param withdrawal
	 * @param withdrawalListJson
	 * @return
	 */
	JsonVo<String> updateWithdrawalStatusBatch(CompanyWithdrawal withdrawal,String withdrawalListJson);
	/**
	 * 申请微信红包提现
	 * @param withdrawal
	 * @param result
	 */
	void withdrawWX(CompanyWithdrawal withdrawal, JsonVo<String> result) throws Exception;
}
