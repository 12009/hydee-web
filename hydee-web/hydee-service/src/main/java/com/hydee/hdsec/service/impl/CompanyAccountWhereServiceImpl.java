package com.hydee.hdsec.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.CompanyAccountWhereDao;
import com.hydee.hdsec.entity.CompanyAccountWhere;
import com.hydee.hdsec.service.CompanyAccountWhereService;
import com.hydee.hdsec.util.EnumUtil;
import com.hydee.hdsec.util.StringUtil;
@Service
public class CompanyAccountWhereServiceImpl implements
		CompanyAccountWhereService {

	@Resource
	CompanyAccountWhereDao companyAccountWhereDao;
	
	@Override
	public List<CompanyAccountWhere> queryAccountWhereListPage(CompanyAccountWhere companyAccountWhere) throws ServiceException {

		return companyAccountWhereDao.queryAccountWhereListPage(companyAccountWhere);
	}

	@Override
	public double selectTotalRedMoney(String chainCustomerId, String userId) throws ServiceException {
		if(StringUtil.isBlanks(false, chainCustomerId, userId)){
			throw new ServiceException(ErrorCodes.UID_CHCID_IS_NULL);
		}
		CompanyAccountWhere accountWhere = new CompanyAccountWhere();
		accountWhere.setChainCustomerId(chainCustomerId);
		accountWhere.setUserId(userId);
		accountWhere.setDictId(EnumUtil.Dict.RED_MONEY.value);
		Double _count = companyAccountWhereDao.countRedMoney(accountWhere);
		return _count == null ? 0 : _count;
	}

	@Override
	public double getSumTaskCharge(String customerId) throws ServiceException {
		return companyAccountWhereDao.getSumTaskCharge(customerId);
	}

}
