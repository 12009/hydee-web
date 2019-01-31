package com.hydee.hdsec.service;

import com.hydee.hdsec.entity.CompanyAccount;

import java.util.List;
import java.util.Map;

/**
 * Created by King.Liu
 * 2016/9/18.
 */
public interface CompanyAccountService {
    public int updateAccountMoney(CompanyAccount companyAccount);

    public List<String> queryMonthTaskByCustomer(Map<String,Object> map) throws Exception;

    public CompanyAccount selectByCustomerId(String id) throws Exception;

    public int getTaskDoingAccount(String customerId) throws Exception;
}
