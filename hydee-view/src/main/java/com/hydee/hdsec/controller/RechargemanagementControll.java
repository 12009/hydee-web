package com.hydee.hdsec.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hydee.hdsec.entity.CompanyAccountRecharge;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hydee.service.CompanyAccountRechargeService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.vo.JsonVo;

@Controller
@RequestMapping(value = "/view/companyAccountRecharge")
public class RechargemanagementControll {

	@Autowired
	CompanyAccountRechargeService companyAccountRechargeService;
	
	@RequestMapping(value = "/updateStatus")
    @ResponseBody
    public Object updateStatus(HttpServletRequest request,Long id, int status) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		CompanyUser _user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		if(!_user.getIsSysAdmin()){
			jsonObj.setResult(false);
			jsonObj.setData("当前用户没有权限!");
			return jsonObj;
		}
		
		int result = companyAccountRechargeService.updateRechargeStruts(id, status);
		
		if(result == 0){
			jsonObj.setResult(false);
			jsonObj.setData("服务器繁忙,稍后请重试!");
		}else{
			jsonObj.setData("修改成功!");
		}
        return jsonObj;
    }


	@RequestMapping(value = "/updateRecharge")
	@ResponseBody
	public Object updateRecharge(HttpServletRequest request,CompanyAccountRecharge companyAccountRecharge) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		CompanyUser _user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		if(!_user.getIsSysAdmin()){
			jsonObj.setResult(false);
			jsonObj.setData("当前用户没有权限!");
			return jsonObj;
		}

		int result = companyAccountRechargeService.updateByPrimaryKeySelective(companyAccountRecharge);

		if(result <= 0){
			jsonObj.setResult(false);
			jsonObj.setData("服务器繁忙,稍后请重试!");
		}
		return jsonObj;
	}
	
	
}
