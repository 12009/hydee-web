package com.hydee.hydee.service;

import java.util.List;

import com.hydee.hdsec.entity.CompanyAccount;
import com.hydee.hdsec.entity.CompanyAccountRecharge;
import com.hydee.hdsec.entity.CompanyUser;
  

public interface CompanyAccountRechargeService {
    

    /**
     * 插入充值数据 
     * @param record 插入的充值数据
     * @return 失败返回0，成功返回1或以上数字
     * */
    int addRecharge(CompanyAccountRecharge record,CompanyUser _user);

    int insertRecharge(CompanyAccountRecharge record);

    /**
     * 分页查询记录充值记录-当前用户
     * @param companyAccountRecharge  
     * @return 返回分页充值记录  失败返回null
     * */
    List<CompanyAccountRecharge> queryRechargeListPage(CompanyAccountRecharge companyAccountRecharge);

    CompanyAccountRecharge selectBySerialNumber(String serialNumber);

     
    /**
     * 修改充值状态
     * @param id 数据ID
     * @param status 充值状态
     * @return 失败返回0，成功返回1或以上数字
     * */
    int updateRechargeStruts(Long id, int status);

    public CompanyAccount selectByCustomerId(String customerId) throws Exception;

    int updateByPrimaryKeySelective(CompanyAccountRecharge companyAccountRecharge) throws Exception;
    
}