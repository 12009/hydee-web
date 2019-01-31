package com.hydee.hdsec.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hydee.hdsec.dao.CompanyAccountDao;
import com.hydee.hdsec.dao.CompanyAccountRechargeDao;
import com.hydee.hdsec.entity.CompanyAccount;
import com.hydee.hdsec.entity.CompanyAccountRecharge;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hdsec.service.CompanyAccountRechargeService;
@Service
public class CompanyAccountRechargeServiceImpl implements
		CompanyAccountRechargeService {

	@Resource
	CompanyAccountRechargeDao companyAccountRechargeDao;
	@Resource
	CompanyAccountDao companyAccountDao;
 
	@Override
	public int addRecharge(CompanyAccountRecharge record,CompanyUser _user) {
		// TODO Auto-generated method stub
	 
		record.setCustomerId(_user.getCustomerId());
		record.setCreateId(_user.getId());
		record.setDictId((long) 6);//目前只支持线下
		record.setSerialNumber(companyAccountRechargeDao.getSerialNumber());
		record.setStatus(0);
		record.setCreateTime(new Date());
		
		
		int result = companyAccountRechargeDao.insert(record);
		
		
		return result;
	}

	@Override
	public List<CompanyAccountRecharge> queryRechargeListPage(CompanyAccountRecharge companyAccountRecharge) {
		// TODO Auto-generated method stub
		return companyAccountRechargeDao.selectListPage(companyAccountRecharge);
	}

	 
 
	@Override
	public int updateRechargeStruts(Long id, int status) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		CompanyAccountRecharge companyAccountRecharge = companyAccountRechargeDao.selectByPrimaryKey(id);
		if(companyAccountRecharge==null)return 0;
		
		//修改账户记录
		CompanyAccount companyAccount = companyAccountDao.selectByCustomerId(companyAccountRecharge.getCustomerId());
		CompanyAccount newCompanyAccount = new CompanyAccount();
		
		newCompanyAccount.setCustomerId(companyAccountRecharge.getCustomerId());
		newCompanyAccount.setAvailableBalance(Double.parseDouble(companyAccountRecharge.getAmount()));
		//判断账户余额存在则修改 不存在则新增
		if(companyAccount==null){
			
			result = companyAccountDao.insert(newCompanyAccount);
			
		}else{
			newCompanyAccount.setId(companyAccount.getId());
			newCompanyAccount.setAvailableBalance(companyAccount.getAvailableBalance()+newCompanyAccount.getAvailableBalance());
			result = companyAccountDao.updateByPrimaryKeySelective(newCompanyAccount);
			//修改
			
			
		}
		if(result !=0){
			//修改状态
			CompanyAccountRecharge record = new CompanyAccountRecharge();
			
			record.setId(id);
			record.setStatus(status);
			result = companyAccountRechargeDao.updateByPrimaryKeySelective(record);
			
		}
		
		
		return result;
		 
	}

	@Override
	public CompanyAccount selectByCustomerId(String customerId) throws Exception {
		return companyAccountDao.selectByCustomerId(customerId);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyAccountRecharge companyAccountRecharge) throws Exception {
		return companyAccountRechargeDao.updateByPrimaryKeySelective(companyAccountRecharge);
	}

}
