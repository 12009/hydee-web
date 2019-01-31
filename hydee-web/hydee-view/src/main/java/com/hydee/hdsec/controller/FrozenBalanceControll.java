package com.hydee.hdsec.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hydee.hdsec.entity.CompanyAccountRecharge;
import com.hydee.hdsec.entity.CompanyRole;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hdsec.service.CompanyTrainTaskService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.util.Tools;
import com.hydee.hdsec.vo.JsonVo;

@Controller
@RequestMapping(value = "/view/frozenBalance")
public class FrozenBalanceControll {

	@Autowired
	CompanyTrainTaskService companyTrainTaskService;
	
	@RequestMapping(value = "/extract")
    @ResponseBody
    public Object extract(HttpServletRequest request,Long taskId) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		CompanyUser _user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		
		//参数判断
		boolean checkOk = true;
		if(taskId==null||taskId<0){
			checkOk = false;
		} 
		if(!checkOk){
			jsonObj.setResult(false);
			jsonObj.setData("参数有误！");
 
			return jsonObj;
		}
		
		 
		int result = companyTrainTaskService.extract(taskId,_user);
		
		if(result == 0){
			jsonObj.setResult(false);
			jsonObj.setData("操作失败!");
		}else{
			jsonObj.setData("操作成功!");
		}
        return jsonObj;
    }
	
}
