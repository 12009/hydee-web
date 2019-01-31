package com.hydee.hydee.service;

import com.hydee.hdsec.entity.CompanyCustomerAccount;

/**
 * Created by King.Liu
 * 2016/9/21.
 */
public interface CompanyCustomerAccountService {

    public int insertCustomerAccount(CompanyCustomerAccount record) throws Exception;

    public CompanyCustomerAccount selectCustomerAccount(String customerId) throws Exception;
}
