package com.hydee.hdsec.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hydee.hdsec.entity.CompanyWithdrawal;

@Repository
public interface CompanyWithdrawalDao {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyWithdrawal record);

    int insertSelective(CompanyWithdrawal record);

    CompanyWithdrawal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyWithdrawal record);

    int updateByPrimaryKey(CompanyWithdrawal record);

	List<CompanyWithdrawal> querySelectiveListPage(CompanyWithdrawal withdrawal);
	/**
	 * 查询用户今日提现次数
	 * @param withdrawal
	 * @return
	 */
	int selectTodayWithdrawCount(CompanyWithdrawal withdrawal);
	/**
	 * 批量更新
	 * @param withdrawalList
	 * @return
	 */
	int updateByPrimaryKeySelectiveBatch(List<CompanyWithdrawal> withdrawalList);
}