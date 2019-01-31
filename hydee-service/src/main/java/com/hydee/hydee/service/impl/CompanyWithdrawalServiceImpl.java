package com.hydee.hydee.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.CompanyUserAccountDao;
import com.hydee.hdsec.dao.CompanyWithdrawalDao;
import com.hydee.hdsec.entity.CompanyUserAccount;
import com.hydee.hdsec.entity.CompanyWithdrawal;
import com.hydee.hydee.service.CompanyWithdrawalService;
import com.hydee.hdsec.util.HttpClientInvokeWX;
import com.hydee.hdsec.util.StringUtil;
import com.hydee.hdsec.vo.JsonVo;

@Service
public class CompanyWithdrawalServiceImpl implements CompanyWithdrawalService {
	// 提现最低限额
	private static final int MIN_WITHDRAWAL = 1;
	
	@Autowired
	CompanyWithdrawalDao withdrawalDao;
	
	@Autowired
    CompanyUserAccountDao companyUserAccountDao;
	
	@Override
	public int insertWithdrawal(CompanyWithdrawal withdrawal) throws ServiceException {
		if(StringUtil.isBlanks(false, withdrawal.getCustomerId(), withdrawal.getUserId(), withdrawal.getBankCard()
			, withdrawal.getUserName(), withdrawal.getCustomerName())){
			throw new ServiceException(ErrorCodes.WITHDRAW_FIALED);
		}else if(withdrawal.getPutMoney() == null || withdrawal.getPutMoney() < MIN_WITHDRAWAL){
			throw new ServiceException(ErrorCodes.WITHDRAW_FIALED_2);
		}
		return withdrawalDao.insertSelective(withdrawal);
	}

	@Override
	public List<CompanyWithdrawal> queryWithdraws(CompanyWithdrawal withdrawal) throws ServiceException {
		if(StringUtil.isBlanks(false, withdrawal.getUserId(), withdrawal.getCustomerId())){
			throw new ServiceException(ErrorCodes.UID_CID_IS_NULL);
		}
		return withdrawalDao.querySelectiveListPage(withdrawal);
	}

	@Override
	public int deleteWithdrawal(CompanyWithdrawal withdrawal) throws ServiceException {
		return withdrawalDao.deleteByPrimaryKey(withdrawal.getId());
	}

	@Override
	public List<CompanyWithdrawal> queryAllWithdraws(CompanyWithdrawal withdrawal) throws ServiceException {
		return withdrawalDao.querySelectiveListPage(withdrawal);
	}

	@Override
	public int selectTodayWithdrawCount(String customerId, String userId) throws ServiceException {
		CompanyWithdrawal withdrawal = new CompanyWithdrawal();
		withdrawal.setCustomerId(customerId);
		withdrawal.setUserId(userId);
		return selectTodayWithdrawCount(withdrawal);
	}

	@Override
	public int updateSelective(CompanyWithdrawal withdrawal) throws ServiceException {
		return withdrawalDao.updateByPrimaryKeySelective(withdrawal);
	}

	@Override
	public int selectTodayWithdrawCount(CompanyWithdrawal withdrawal) throws ServiceException {
		if(StringUtil.isBlanks(false, withdrawal.getUserId(), withdrawal.getCustomerId())){
			throw new ServiceException(ErrorCodes.UID_CID_IS_NULL);
		}
		return withdrawalDao.selectTodayWithdrawCount(withdrawal);
	}

	@Override
	public int updateSelectiveBatch(List<CompanyWithdrawal> withdrawalList) {
		return withdrawalDao.updateByPrimaryKeySelectiveBatch(withdrawalList);
	}

	@Override
	public JsonVo<String> updateWithdrawalStatusBatch(CompanyWithdrawal withdrawal, String withdrawalListJson) {
		JsonVo<String> result = new JsonVo<String>();
		List<CompanyWithdrawal> withdrawalList = new ArrayList<CompanyWithdrawal>();
        JSONArray withdrawalListJobjs = JSONArray.fromObject(withdrawalListJson);
		Iterator<JSONObject> itr = withdrawalListJobjs.iterator();
        while(itr.hasNext()){
        	CompanyWithdrawal _withdrawal = (CompanyWithdrawal) JSONObject.toBean(itr.next(),CompanyWithdrawal.class);
        	_withdrawal.setRemitTime(new Date());
        	_withdrawal.setRemittorId(withdrawal.getRemittorId());
        	_withdrawal.setRemittorName(withdrawal.getRemittorName());
        	withdrawalList.add(_withdrawal);
        }
        int flag = withdrawalDao.updateByPrimaryKeySelectiveBatch(withdrawalList);
        if(flag == 0){
        	result.setResult(false);
        	result.setData("操作失败,稍后请重试!");
        }else{
        	if(withdrawal.getStatus() == 1){
            	// 打款审核不通过，返还提现金额
        		List<CompanyUserAccount> userAccountList = new ArrayList<CompanyUserAccount>();
        		for(CompanyWithdrawal _withdrawal: withdrawalList){
        			CompanyUserAccount _userAccount = new CompanyUserAccount();
        			_userAccount.setBalance(_withdrawal.getPutMoney() == null ? 0 : -_withdrawal.getPutMoney());
        			_userAccount.setUserId(_withdrawal.getUserId());
        			_userAccount.setCustomerId(_withdrawal.getCustomerId());
        			userAccountList.add(_userAccount);
        		}
        		int takeoutCount = companyUserAccountDao.takeoutBalanceBatch(userAccountList);
        		if(takeoutCount == 0){
        			result.setResult(false);
                	result.setData("操作失败,稍后请重试!");
        		}else{
        			result.setData("操作成功!");
        		}
            }else{
            	result.setData("操作成功!");
            }
        }
		return result;
	}

	@Override
	public void withdrawWX(CompanyWithdrawal withdrawal, JsonVo<String> result) throws Exception {
		// 插入数据
		int flag = insertWithdrawal(withdrawal);
		if(flag == 0){
			result.setResult(false);
			result.setData("提现失败,稍后请重试!");
		}else{
			// 扣除用户可用余额
			CompanyUserAccount userAccount = new CompanyUserAccount();
			userAccount.setUserId(withdrawal.getUserId());
			userAccount.setCustomerId(withdrawal.getCustomerId());
			userAccount.setBalance(withdrawal.getPutMoney());
			int takeoutCount = companyUserAccountDao.takeoutBalance(userAccount);
			if(takeoutCount == 0){
				// 扣除失败时回滚
				deleteWithdrawal(withdrawal);
				result.setResult(false);
				result.setData("提现失败,稍后请重试!");
			}else{
				// 调用微信接口发送红包
				Map<String, String> resultMap = HttpClientInvokeWX.sendRedpack(withdrawal.getWxOpenid(), withdrawal.getPutMoney(), withdrawal.getSerialNumber());
				if(resultMap.get("result_code").trim().equals("FAIL")){
					deleteWithdrawal(withdrawal);
					throw new ServiceException(ErrorCodes.WX_REQ_ERRORS, resultMap.get("err_code_des"));
				}
				result.setData("提现成功!");
			}
		}
	}

}
