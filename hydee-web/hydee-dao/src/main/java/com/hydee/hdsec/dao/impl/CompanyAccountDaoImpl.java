package com.hydee.hdsec.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.hydee.hdsec.dao.CompanyAccountDao;
import com.hydee.hdsec.dao.base.BaseDao;
import com.hydee.hdsec.entity.CompanyAccount;

import java.util.List;
import java.util.Map;

@Repository("companyAccountDao")
public class CompanyAccountDaoImpl extends BaseDao implements CompanyAccountDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return (Integer)delete("com.hydee.hdsec.dao.CompanyAccountDao.deleteByPrimaryKey",id);
	}

	@Override
	public int insert(CompanyAccount record) {
		// TODO Auto-generated method stub
		return (Integer)save("com.hydee.hdsec.dao.CompanyAccountDao.insert",record);
	}

	@Override
	public int insertSelective(CompanyAccount record) {
		// TODO Auto-generated method stub
		return (Integer)save("com.hydee.hdsec.dao.CompanyAccountDao.insertSelective",record);
	}

	@Override
	public CompanyAccount selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return (CompanyAccount)findForObject("com.hydee.hdsec.dao.CompanyAccountDao.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyAccount record) {
		// TODO Auto-generated method stub
		return (Integer)update("com.hydee.hdsec.dao.CompanyAccountDao.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(CompanyAccount record) {
		// TODO Auto-generated method stub
		return (Integer)update("com.hydee.hdsec.dao.CompanyAccountDao.updateByPrimaryKey",record);
	}

	@Override
	public List<String> queryMonthTaskByCustomer(Map<String, Object> map) {
		return (List<String>) findForList("com.hydee.hdsec.dao.CompanyAccountDao.queryMonthTaskByCustomer",map);
	}

	@Override
	public int getTaskDoingAccount(String customerId) {
		return (Integer) findForObject("com.hydee.hdsec.dao.CompanyAccountDao.getTaskDoingAccount",customerId);
	}

	@Override
	public CompanyAccount selectByCustomerId(String id) {
		// TODO Auto-generated method stub
		return (CompanyAccount)findForObject("com.hydee.hdsec.dao.CompanyAccountDao.selectByCustomerId", id);
	}

}
