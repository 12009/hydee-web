package com.hydee.hydee.service.impl;

import com.hydee.hdsec.dao.CompanyAccountDao;
import com.hydee.hdsec.entity.CompanyAccount;
import com.hydee.hydee.service.CompanyAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by King.Liu
 * 2016/9/18.
 */
@Service
public class CompanyAccountServiceImpl implements CompanyAccountService{
    @Resource
    CompanyAccountDao companyAccountDao;

    @Override
    public int updateAccountMoney(CompanyAccount companyAccount) {
        double _availableBalance = companyAccount.getAvailableBalance();
        double _balance = companyAccount.getBalance();
        double _money = 0;
        if(1 == companyAccount.getsType()){//新增余额
            _money = _availableBalance+_balance;
        }else if(2 == companyAccount.getsType()){//减少
            _money = _availableBalance-_balance;
        }else{
            _money = _availableBalance;
        }
        companyAccount.setAvailableBalance(_money);
        return companyAccountDao.updateByPrimaryKeySelective(companyAccount);
    }

    @Override
    public List<String> queryMonthTaskByCustomer(Map<String, Object> map) throws Exception {
        return companyAccountDao.queryMonthTaskByCustomer(map);
    }

    @Override
    public CompanyAccount selectByCustomerId(String id) throws Exception {
        return companyAccountDao.selectByCustomerId(id);
    }

    @Override
    public int getTaskDoingAccount(String customerId) throws Exception {
        return companyAccountDao.getTaskDoingAccount(customerId);
    }
}
