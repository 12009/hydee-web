package com.hydee.hdsec.controller.interfaceSec;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.controller.BaseController;
import com.hydee.hdsec.entity.CompanyAccountWhere;
import com.hydee.hdsec.entity.CompanyBindBank;
import com.hydee.hdsec.entity.CompanyUserAccount;
import com.hydee.hdsec.entity.CompanyWithdrawal;
import com.hydee.hdsec.service.CompanyAccountWhereService;
import com.hydee.hdsec.service.CompanyBindBankService;
import com.hydee.hdsec.service.CompanyUserAccountService;
import com.hydee.hdsec.service.CompanyWithdrawalService;
import com.hydee.hdsec.util.BankCardBin;
import com.hydee.hdsec.util.EnumUtil;
import com.hydee.hdsec.util.StringUtil;
import com.hydee.hdsec.vo.JsonVo;

/**
 * 小蜜钱包接口类
 * Created by King.Liu
 * 2016/9/19.
 */
@Controller
@RequestMapping(value = "/secWallet")
public class SecWalletInterfaceController extends BaseController{
    @Autowired
    CompanyBindBankService companyBindBankService;
    @Autowired
    CompanyUserAccountService companyUserAccountService;
    @Autowired
    CompanyAccountWhereService companyAccountWhereService;
    @Autowired
    CompanyWithdrawalService companyWithdrawalService;
    
    /**
     * 查询钱包基础信息
     * @param chainCustomerId
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/balanceDetail")
    @ResponseBody
    public Object balanceDetail(String chainCustomerId,String userId) throws Exception{
    	JsonVo<Map<String,Object>> result = new JsonVo<Map<String,Object>>();
    	double limitBalance = companyUserAccountService.selectUserBalance(chainCustomerId,userId);
    	double totalBalance = companyAccountWhereService.selectTotalRedMoney(chainCustomerId,userId);
    	CompanyBindBank lastUsefulBankCard = new CompanyBindBank();
    	lastUsefulBankCard.setCustomerId(chainCustomerId);
    	lastUsefulBankCard.setUserId(userId);
    	lastUsefulBankCard = companyBindBankService.selectLastUsefulBankCard(lastUsefulBankCard);
    	int withdrawCount = companyWithdrawalService.selectTodayWithdrawCount(chainCustomerId,userId);
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("limitBalance", limitBalance);
    	map.put("totalBalance", totalBalance);
    	map.put("lastUsefulBankCard", lastUsefulBankCard);
    	map.put("withdrawCount", withdrawCount);
    	result.setData(map);
    	return result;
    }
    
    /**
     * 查询小蜜钱包余额
     * @param chainCustomerId
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getLimitBalance")
    @ResponseBody
    public Object getLimitBalance(String chainCustomerId,String userId) throws Exception{
    	JsonVo<Double> result = new JsonVo<Double>();
    	double limitBalance = companyUserAccountService.selectUserBalance(chainCustomerId,userId);
    	result.setData(limitBalance);
    	return result;
    }
    
    /**
     * 查询获取的赏金记录
     * @param accountWhere(customerId,userId)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/listRewards")
    @ResponseBody
    public Object listAccountWhere(CompanyAccountWhere accountWhere) throws Exception{
    	if(StringUtil.isBlanks(false, accountWhere.getChainCustomerId(), accountWhere.getUserId())){
			throw new ServiceException(ErrorCodes.UID_CHCID_IS_NULL);
		}
    	JsonVo<List<Map<String,Object>>> result = new JsonVo<List<Map<String,Object>>>();
        accountWhere.setDictId(EnumUtil.Dict.RED_MONEY.value);
    	List<CompanyAccountWhere> reList = companyAccountWhereService.queryAccountWhereListPage(accountWhere);
    	// 简化输出字段
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for(CompanyAccountWhere _accountWhere : reList){
    		Map<String,Object> _map = new HashMap<String, Object>();
    		_map.put("createTimeFmt", _accountWhere.getCreateTimeFmt());
    		_map.put("reward", _accountWhere.getRedMoney());
    		_map.put("dictName", _accountWhere.getDictName());
    		list.add(_map);
    	}
    	result.setAllcount(accountWhere.getTotalResult());
    	result.setData(list);
    	return result;
    }
    
    /**
     * 获取用户绑定的银行卡列表
     * @param bankCard
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/listBankCards")
    @ResponseBody
    public Object listBankCards(CompanyBindBank bankCard) throws Exception{
    	JsonVo<List<CompanyBindBank>> result = new JsonVo<List<CompanyBindBank>>();
    	List<CompanyBindBank> reList = companyBindBankService.selectBindBankByCidAndUserId(bankCard);
    	result.setAllcount(bankCard.getTotalResult());
    	result.setData(reList);
    	return result;
    }
    
    /**
     * 绑定银行卡
     * @param bankCard
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/bindBankCards")
    @ResponseBody
    public Object bindBankCards(CompanyBindBank bankCard) throws Exception{
    	JsonVo<String> result = new JsonVo<String>();
    	// 查询用户是否已绑定过此银行卡
    	int flag = companyBindBankService.findBindBankCard(bankCard);
    	if(flag > 0){
    		throw new ServiceException(ErrorCodes.BANK_CARD_IS_BIND);
    	}
    	int addCount = companyBindBankService.insertBindBank(bankCard);
    	if(addCount == 0){
    		result.setResult(false);
    		result.setData("绑定失败,请核对银行卡信息");
    	}else{
    		result.setData("绑定成功!");
    	}
    	return result;
    }
    
    /**
     * 删除绑定的银行卡
     * @param id
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unbindBankCard")
    @ResponseBody
    public Object unbindBank(Long id, Model model, HttpServletRequest request) throws Exception{
        JsonVo<String> jsonObj = new JsonVo<String>();
        int flag = companyBindBankService.deleteBindBank(id);
        if(flag <= 0){
            jsonObj.setResult(false);
            jsonObj.setData("解绑失败,稍后请重试!");
        }
        jsonObj.setData("解绑成功!");
        return jsonObj;
    }
    /**
     * APP申请提现
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/withdraw")
    @ResponseBody
    public Object withdraw(CompanyWithdrawal withdrawal,Long bindBankId, Model model, HttpServletRequest request) throws Exception{
        JsonVo<String> result = new JsonVo<String>();
        // 查询近日提现次数
        int withdrawCount = companyWithdrawalService.selectTodayWithdrawCount(withdrawal);
        // 提现次数大于1次或者不是周二则不能提现
        if(withdrawCount > 0 || Calendar.getInstance().get(Calendar.DAY_OF_WEEK) != 3){
        	throw new ServiceException(ErrorCodes.WITHDRAW_FIALED_3);
        }
        CompanyBindBank bindBank = companyBindBankService.selectById(bindBankId);
        if(bindBank == null){
        	throw new ServiceException(ErrorCodes.WITHDRAW_FIALED_4);
        }
        withdrawal.setBankCard(bindBank.getBankCard());
        withdrawal.setBankName(bindBank.getBankName());
        withdrawal.setUserName(bindBank.getOwnerName());
        withdrawal.setStatus(EnumUtil.WithdrawalStatus.STATUS_AUTHSTR.value);
        int flag = companyWithdrawalService.insertWithdrawal(withdrawal);
        if(flag == 0){
        	result.setResult(false);
        	result.setData("申请失败,稍后请重试!");
        }else{
        	try{
	        	// 扣除用户可用余额
	        	CompanyUserAccount userAccount = new CompanyUserAccount();
	        	userAccount.setUserId(withdrawal.getUserId());
	        	userAccount.setCustomerId(withdrawal.getCustomerId());
	        	userAccount.setBalance(withdrawal.getPutMoney());
        		int takeoutCount = companyUserAccountService.takeoutBalance(userAccount);
        		if(takeoutCount == 0){
        			// 扣除失败时回滚
        			companyWithdrawalService.deleteWithdrawal(withdrawal);
            		result.setResult(false);
            		result.setData("申请失败,稍后请重试!");
        		}else{
        			result.setData("申请成功!");
        		}
        	}catch(Exception ex){
        		// 扣除失败时回滚
        		companyWithdrawalService.deleteWithdrawal(withdrawal);
        		result.setResult(false);
        		result.setData("申请失败,稍后请重试!");
        	}
        }
        return result;
    }
    
    /**
     * 查询提现列表(APP端用户查询用)
     * @param withdrawal
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/listWithdraws")
    @ResponseBody
    public Object listWithdraws(CompanyWithdrawal withdrawal, Model model, HttpServletRequest request) throws Exception{
        JsonVo<List<CompanyWithdrawal>> result = new JsonVo<List<CompanyWithdrawal>>();
        List<CompanyWithdrawal> reList = companyWithdrawalService.queryWithdraws(withdrawal);
        result.setAllcount(withdrawal.getTotalResult());
        result.setData(reList);
        return result;
    }
    /**
     * 查询提现列表(小蜜超管打款用)
     * @param withdrawal
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/listAllWithdraws")
    @ResponseBody
    public Object listAllWithdraws(CompanyWithdrawal withdrawal, Model model, HttpServletRequest request) throws Exception{
        JsonVo<List<CompanyWithdrawal>> result = new JsonVo<List<CompanyWithdrawal>>();
        List<CompanyWithdrawal> reList = companyWithdrawalService.queryAllWithdraws(withdrawal);
        result.setAllcount(withdrawal.getTotalResult());
        result.setData(reList);
        return result;
    }
    /**
     * 更新提现状态
     * @param withdrawal
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateWithdrawalStatus")
    @ResponseBody
    public Object updateWithdrawalStatus(CompanyWithdrawal withdrawal, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
        JsonVo<String> result = new JsonVo<String>();
        if(withdrawal.getStatus() == 2){
        	// 更新打款时间
        	withdrawal.setRemitTime(new Date());
        }
        int flag = companyWithdrawalService.updateSelective(withdrawal);
        if(flag == 0){
        	result.setResult(false);
        	result.setData("操作失败,稍后请重试!");
        }else{
        	if(withdrawal.getStatus() == 1){
            	// 打款审核不通过，返还提现金额
        		CompanyUserAccount _userAccount = new CompanyUserAccount();
        		_userAccount.setBalance(withdrawal.getPutMoney() == null ? 0 : -withdrawal.getPutMoney());
        		_userAccount.setUserId(withdrawal.getUserId());
        		_userAccount.setCustomerId(withdrawal.getCustomerId());
        		int takeoutCount = companyUserAccountService.takeoutBalance(_userAccount);
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
    
    @RequestMapping(value = "/updateWithdrawalStatusBatch")
    @ResponseBody
    public Object updateWithdrawalStatusBatch(CompanyWithdrawal withdrawal, String withdrawalListJson, Model model, HttpServletResponse response, HttpServletRequest request) throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
        return companyWithdrawalService.updateWithdrawalStatusBatch(withdrawal, withdrawalListJson);
    }
    /**
     * 获取银行卡对应银行字典
     * @param version
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/bankNumberDict")
    @ResponseBody
    public Object bankNumberDict(Double version, Model model, HttpServletRequest request) throws Exception{
        JsonVo<Map<String,Object>> result = new JsonVo<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        if(version == null || version < BankCardBin.VERSION){
        	// 字典版本已更新
        	map.put("version", BankCardBin.VERSION);
        	Map<String,String> dict = new HashMap<String,String>();
        	for(int i=0;i<BankCardBin.BANK_BIN.length;i++){
        		String _bankName = BankCardBin.BANK_NAME[i];
        		int _lastIndex = _bankName.indexOf("·");
        		_lastIndex = _lastIndex < 0 ? _bankName.length() : _lastIndex;
        		dict.put(String.valueOf(BankCardBin.BANK_BIN[i]), _bankName.substring(0,_lastIndex));
        	}
        	map.put("dict", dict);
        }else{
        	result.setResult(false);
        }
        result.setData(map);
        return result;
    }
}
