package com.hydee.hdsec.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hydee.hdsec.entity.CompanyAccountRecharge;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hydee.service.CompanyAccountRechargeService;
import com.hydee.hdsec.vo.JsonVo;

import java.util.Map;

@Controller
@RequestMapping(value = "/view/accountRecharge")
public class AccountRechargeControll {

	@Autowired
	CompanyAccountRechargeService companyAccountRechargeService;
	/** 对公打开 **/
	private static final String DUIGONGDAKUAN = "/system/accountInformation/duigongdakuan";
	/** 二维码  **/
	private static final String WXOCODE = "/system/myTraining/wxOcode";

	//对公打款
	@RequestMapping(value = "/duigongdakuan")
	public String duigongdakuan(HttpServletRequest request,Model model) throws Exception{
		return DUIGONGDAKUAN;
	}

	@RequestMapping(value = "/wxOcode")
	public String wxOcode(String totalFee,Model model,HttpServletRequest request,HttpServletResponse rsp) throws Exception {
		if("".equals(totalFee)){
			throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
		}
		if(Double.parseDouble(totalFee) < 1000){
			throw new ServiceException(ErrorCodes.WECHAT_RECHARGE_ASTRICT);
		}
		CompanyUser _user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		String outTradeNo = HttpClientWX.getNonceStr();
		// 构造参数调用统一下单接口
		String result = HttpClientWX.unifiedOrder(outTradeNo,totalFee,request);// 调用微信支付统一接口
		Map<String, String> map = HttpClientInvokeWX.resolveXmlString(result);
		if(!"SUCCESS".equals(map.get("return_code"))){
			throw new ServiceException(map.get("return_msg"));
		}
		Double _fee = Double.parseDouble(totalFee);
		String _totalFee = String.valueOf(_fee / 100);
		CompanyAccountRecharge accountRecharge = new CompanyAccountRecharge();
		accountRecharge.setAmount(_totalFee);
		accountRecharge.setCreateId(_user.getId());
		accountRecharge.setDictId(EnumUtil.PaymentType.WECHAT.value);
		accountRecharge.setCustomerId(_user.getCustomerId());
		accountRecharge.setStatus(2);
		accountRecharge.setPhone(_user.getUserPhone());
		accountRecharge.setSerialNumber(outTradeNo);
		int flag = companyAccountRechargeService.insertRecharge(accountRecharge);
		if(flag == 0){
			throw new ServiceException("新增账单失败！");
		}
		model.addAttribute("outTradeNo",outTradeNo);
		model.addAttribute("qcodeResult",result);
		model.addAttribute("totalFee",totalFee);
		return WXOCODE;
	}

	@RequestMapping(value = "/addRecharge")
    @ResponseBody
    public Object addRecharge(HttpServletRequest request,String accountName,String bankCard,String bankName,String amount,String phone,String transferProof) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		CompanyUser _user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);

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
