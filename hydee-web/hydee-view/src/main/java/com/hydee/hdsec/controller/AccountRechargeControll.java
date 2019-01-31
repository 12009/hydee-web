package com.hydee.hdsec.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hydee.hdsec.entity.CompanyAccountRecharge;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hdsec.service.CompanyAccountRechargeService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.util.Tools;
import com.hydee.hdsec.vo.JsonVo;

@Controller
@RequestMapping(value = "/view/accountRecharge")
public class AccountRechargeControll {

	@Autowired
	CompanyAccountRechargeService companyAccountRechargeService;
	
	
	@RequestMapping(value = "/addRecharge")
    @ResponseBody
    public Object addRecharge(HttpServletRequest request,String accountName,String bankCard,String bankName,String amount,String phone,String transferProof) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		CompanyUser _user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		
		//参数判断
		boolean checkOk = true;
		if(accountName==null||accountName.equals("")||accountName.length()>20){
			checkOk = false;
		}else if(bankName==null||bankName.equals("")||bankName.length()>20){
			checkOk = false;
		}else if(bankCard==null||bankCard.equals("")||(bankCard.length()!=16 && bankCard.length()!=19)){
			checkOk = false;
		}else if(amount==null||amount.equals("")||!Tools.checkNumeric(amount)){
			checkOk = false;
		}else if(phone==null||phone.equals("")){
			checkOk = false;
		}
		if(!checkOk){
			jsonObj.setResult(false);
			jsonObj.setData("参数有误！");
 
			return jsonObj;
		}
		
		CompanyAccountRecharge companyAccountRecharge = new CompanyAccountRecharge();
		companyAccountRecharge.setAccountName(accountName);
		companyAccountRecharge.setBankName(bankName);
		companyAccountRecharge.setBankCard(bankCard);
		companyAccountRecharge.setAmount(amount);
		companyAccountRecharge.setPhone(phone);
		companyAccountRecharge.setTransferProof(transferProof);
		int result = companyAccountRechargeService.addRecharge(companyAccountRecharge,_user);
		
		if(result == 0){
			jsonObj.setResult(false);
			jsonObj.setData("服务器繁忙,稍后请重试!");
		}else{
			jsonObj.setData("操作成功!");
		}
        return jsonObj;
    }
	
}
