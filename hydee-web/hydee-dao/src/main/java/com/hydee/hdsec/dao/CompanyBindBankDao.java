package com.hydee.hdsec.dao;


import com.hydee.hdsec.entity.CompanyBindBank;

import java.util.List;

public interface CompanyBindBankDao {

    /**
     * 根据customerId和userId查询出用户绑定的卡
     * @param bindBank
     * @return
     */
	List<CompanyBindBank> selectBindBankByCidAndUserId(CompanyBindBank bindBank);

    /**
     * 删除绑定卡
     * @param id
     * @return
     */
	int deleteBindBank(Long id);

    /**
     * 新增绑定卡
     * @param bindBank
     * @return
     */
	int insertBindBank(CompanyBindBank bindBank);

    /**
     * 编辑绑定卡
     * @param bindBank
     * @return
     */
	int updateBindBank(CompanyBindBank bindBank);
	
	/**
	 * 查询银行卡详情
	 * @param id
	 * @return
	 */
	CompanyBindBank selectById(Long id);
	
	/**
	 * 查询此卡是否被用户已经绑定过
	 * @param bankCard
	 * @return
	 */
	int selectCardByBankCard(CompanyBindBank bankCard);
	
	/**
	 * 查询用户最后提现过的有效银行卡
	 * @param bindBank
	 * @return
	 */
	CompanyBindBank selectLastUsefulBankCard(CompanyBindBank bindBank);
}