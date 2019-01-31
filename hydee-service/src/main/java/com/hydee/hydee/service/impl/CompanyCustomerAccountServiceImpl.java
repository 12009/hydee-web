package com.hydee.hydee.service.impl;

import com.hydee.hdsec.dao.CompanyCustomerAccountDao;
import com.hydee.hdsec.entity.CompanyCustomerAccount;
import com.hydee.hydee.service.CompanyCustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by King.Liu
 * 2016/9/21.
 */
@Service
public class CompanyCustomerAccountServiceImpl implements CompanyCustomerAccountService {
    @Autowired
    CompanyCustomerAccountDao companyCustomerAccountDao;

    @Override
    public int insertCustomerAccount(CompanyCustomerAccount record) throws Exception {
        return companyCustomerAccountDao.insertCustomerAccount(record);
    }

    @Override
    public CompanyCustomerAccount selectCustomerAccount(String customerId) throws Exception {
        return companyCustomerAccountDao.selectCustomerAccount(customerId);
    }
}
