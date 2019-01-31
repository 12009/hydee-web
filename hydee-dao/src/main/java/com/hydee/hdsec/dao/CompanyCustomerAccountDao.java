package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.CompanyCustomerAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCustomerAccountDao {
    int deleteCustomerAccount(Long id);

    int insertCustomerAccount(CompanyCustomerAccount record);

    CompanyCustomerAccount selectCustomerAccount(String customerId);

    int updateCustomerAccount(CompanyCustomerAccount record);

}