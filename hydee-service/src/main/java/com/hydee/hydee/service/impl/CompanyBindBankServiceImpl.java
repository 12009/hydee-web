package com.hydee.hydee.service.impl;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.CompanyBindBankDao;
import com.hydee.hdsec.entity.CompanyBindBank;
import com.hydee.hydee.service.CompanyBindBankService;
import com.hydee.hdsec.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by King.Liu
 * 2016/9/19.
 */
@Service
public class CompanyBindBankServiceImpl implements CompanyBindBankService{
    @Autowired
    CompanyBindBankDao companyBindBankDao;


    @Override
    public List<CompanyBindBank> selectBindBankByCidAndUserId(CompanyBindBank bindBank) throws Exception {
    	if(StringUtil.isBlanks(false, bindBank.getUserId(), bindBank.getCustomerId())){
    		throw new ServiceException(ErrorCodes.UID_CID_IS_NULL);
    	}
        return companyBindBankDao.selectBindBankByCidAndUserId(bindBank);
    }

    @Override
    public int deleteBindBank(Long id) throws Exception {
        return companyBindBankDao.deleteBindBank(id);
    }

    @Override
    public int insertBindBank(CompanyBindBank bindBank) throws Exception {
    	if(StringUtil.isBlanks(false, bindBank.getCustomerId(), bindBank.getUserId(), bindBank.getBankCard(),
    			bindBank.getBankName(), bindBank.getOwnerName())){
    		throw new ServiceException(ErrorCodes.BIND_BANK_CARD_ERROR);
    	}
        return companyBindBankDao.insertBindBank(bindBank);
    }

	@Override
	public CompanyBindBank selectById(Long id) throws Exception {
		return companyBindBankDao.selectById(id);
	}

	@Override
	public int findBindBankCard(CompanyBindBank bankCard) throws Exception {
		return companyBindBankDao.selectCardByBankCard(bankCard);
	}

	@Override
	public CompanyBindBank selectLastUsefulBankCard(CompanyBindBank bindBank) throws Exception {
		if(StringUtil.isBlanks(false, bindBank.getUserId(), bindBank.getCustomerId())){
    		throw new ServiceException(ErrorCodes.UID_CID_IS_NULL);
    	}
		return companyBindBankDao.selectLastUsefulBankCard(bindBank);
	}
}
