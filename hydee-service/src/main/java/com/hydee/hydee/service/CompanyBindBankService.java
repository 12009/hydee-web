package com.hydee.hydee.service;

import com.hydee.hdsec.entity.CompanyBindBank;

import java.util.List;

/**
 * Created by King.Liu
 * 2016/9/19.
 */
public interface CompanyBindBankService {

    /**
     * 根据customerId和userId查询出用户绑定的卡
     * @param bindBank
     * @return
     */
    List<CompanyBindBank> selectBindBankByCidAndUserId(CompanyBindBank bindBank) throws Exception;

    /**
     * 删除绑定卡
     * @param id
     * @return
     */
    int deleteBindBank(Long id) throws Exception;

    /**
     * 新增绑定卡
     * @param bindBank
     * @return
     */
    int insertBindBank(CompanyBindBank bindBank) throws Exception;
    
    /**
     * 查询绑定银行卡详情
     * @param id
     * @return
     * @throws Exception
     */
    CompanyBindBank selectById(Long id) throws Exception;
    
    /**
     * 查询银行卡号
     * @param bankCard
     * @return
     * @throws Exception
     */
	int findBindBankCard(CompanyBindBank bankCard) throws Exception;
	
	/**
	 * 获取最后使用的有效银行卡
	 * @param customerId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	CompanyBindBank selectLastUsefulBankCard(CompanyBindBank bindBank) throws Exception;
}
