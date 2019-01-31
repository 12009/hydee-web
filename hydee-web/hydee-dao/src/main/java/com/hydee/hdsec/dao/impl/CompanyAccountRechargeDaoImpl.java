package com.hydee.hdsec.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hydee.hdsec.dao.CompanyAccountRechargeDao;
import com.hydee.hdsec.dao.base.BaseDao;
import com.hydee.hdsec.entity.CompanyAccountRecharge;
import com.hydee.hdsec.entity.CompanyTrainTask;
import com.hydee.hdsec.entity.Page;
 
@Repository("companyAccountRechargeDao")
public class CompanyAccountRechargeDaoImpl  extends BaseDao implements CompanyAccountRechargeDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return (Integer)delete("com.hydee.hdsec.dao.CompanyAccountRechargeDao.deleteByPrimaryKey",id);
	}

	@Override
	public int insert(CompanyAccountRecharge record) {
		// TODO Auto-generated method stub
		return (Integer)save("com.hydee.hdsec.dao.CompanyAccountRechargeDao.insert",record);
	}

	@Override
	public int insertSelective(CompanyAccountRecharge record) {
		// TODO Auto-generated method stub
		return (Integer)save("com.hydee.hdsec.dao.CompanyAccountRechargeDao.insertSelective",record);
	}

	@Override
	public CompanyAccountRecharge selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return (CompanyAccountRecharge)findForObject("com.hydee.hdsec.dao.CompanyAccountRechargeDao.selectByPrimaryKey", id);
	}

	@Override
	public String getSerialNumber() {
		// TODO Auto-generated method stub
		return (String) findForObject("com.hydee.hdsec.dao.CompanyAccountRechargeDao.getSerialNumber");
	}

	
	@Override
	public List<CompanyAccountRecharge> selectListPage(CompanyAccountRecharge companyAccountRecharge) {
		// TODO Auto-generated method stub
		 return (List<CompanyAccountRecharge>) findForList("com.hydee.hdsec.dao.CompanyAccountRechargeDao.selectListPage",companyAccountRecharge);
	}


	@Override
	public CompanyAccountRecharge selectBySerialNumber(String serialNumber) {
		// TODO Auto-generated method stub
		return (CompanyAccountRecharge)findForObject("com.hydee.hdsec.dao.CompanyAccountRechargeDao.selectBySerialNumber",serialNumber);
	}
	
	@Override
	public int updateByPrimaryKeySelective(CompanyAccountRecharge record) {
		// TODO Auto-generated method stub
		return (Integer)update("com.hydee.hdsec.dao.CompanyAccountRechargeDao.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(CompanyAccountRecharge record) {
		// TODO Auto-generated method stub
		return (Integer)update("com.hydee.hdsec.dao.CompanyAccountRechargeDao.updateByPrimaryKey",record);
	}

	@Override
	public int updateBySerialNumberSelective(CompanyAccountRecharge record) {
		// TODO Auto-generated method stub
		 
		return (Integer)update("com.hydee.hdsec.dao.CompanyAccountRechargeDao.updateBySerialNumberSelective",record);
	}

	
	

}
