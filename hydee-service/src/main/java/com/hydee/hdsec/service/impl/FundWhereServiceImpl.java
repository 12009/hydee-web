package com.hydee.hdsec.service.impl;

import com.hydee.hdsec.service.FundWhereService;
import com.hydee.hdsec.sqlDao.FundWhereDao;
import com.hydee.hdsec.vo.FundWhereVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 94908 on 2017/07/14.
 */
@Service
public class FundWhereServiceImpl implements FundWhereService{
	@Autowired
	FundWhereDao fundWhereDao;

	@Override
	public Double countMaterialRedMoney(FundWhereVo fundWhereVo) {
		return fundWhereDao.countMaterialRedMoney(fundWhereVo);
	}
}
