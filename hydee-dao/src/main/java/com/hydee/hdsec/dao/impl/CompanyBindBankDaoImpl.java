package com.hydee.hdsec.dao.impl;

import com.hydee.hdsec.dao.CompanyBindBankDao;
import com.hydee.hdsec.dao.CompanyUserDao;
import com.hydee.hdsec.dao.base.BaseDao;
import com.hydee.hdsec.entity.CompanyBindBank;
import com.hydee.hdsec.entity.CompanyUser;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MrLiu on 2016/9/19.
 */
@Repository("companyBindBankDao")
public class CompanyBindBankDaoImpl extends BaseDao implements CompanyBindBankDao {

    @Override
    public List<CompanyBindBank> selectBindBankByCidAndUserId(CompanyBindBank bindBank) {
        return (List<CompanyBindBank>) findForList("CompanyBindBankMapper.selectBindBankByCidAndUserId",bindBank);
    }

    @Override
    public int deleteBindBank(Long id) {
        return (Integer) delete("CompanyBindBankMapper.deleteBindBank",id);
    }

    @Override
    public int insertBindBank(CompanyBindBank bindBank) {
        return (Integer) delete("CompanyBindBankMapper.insertBindBank",bindBank);
    }

    @Override
    public int updateBindBank(CompanyBindBank bindBank) {
        return (Integer) delete("CompanyBindBankMapper.updateBindBank",bindBank);
    }

	@Override
	public CompanyBindBank selectById(Long id) {
		return (CompanyBindBank) findForObject("CompanyBindBankMapper.selectById", id);
	}

	@Override
	public int selectCardByBankCard(CompanyBindBank bankCard) {
		return (Integer) findForObject("CompanyBindBankMapper.selectCardByBankCard", bankCard);
	}

	@Override
	public CompanyBindBank selectLastUsefulBankCard(CompanyBindBank bindBank) {
		return (CompanyBindBank) findForObject("CompanyBindBankMapper.selectLastUsefulBankCard", bindBank);
	}
}
