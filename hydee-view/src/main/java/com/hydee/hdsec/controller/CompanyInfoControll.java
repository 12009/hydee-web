package com.hydee.hdsec.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hydee.hdsec.entity.CompanyOrganization;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hydee.service.CompanyOrgService;
import com.hydee.hydee.service.CompanyUserService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.vo.JsonVo;

@Controller
@RequestMapping(value = "/view/companyInformation")
public class CompanyInfoControll {
	
	@Autowired
    CompanyUserService userService;
	@Autowired
	CompanyOrgService orgService;
	
	@RequestMapping(value = "/updateAdmin")
    @ResponseBody
    public Object updateAdmin(CompanyUser user,HttpServletRequest request) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		CompanyUser _user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		// 保存用户信息
		user.setModifiedId(_user.getId());
		int count = userService.saveOrUpdate(user);
		if(count == 0){
			jsonObj.setResult(false);
			jsonObj.setData("服务器繁忙,稍后请重试!");
		}else{
			jsonObj.setData("账户保存成功!");
		}
        return jsonObj;
    }
	
	@RequestMapping(value = "/updateCompInfo")
    @ResponseBody
    public Object updateCompInfo(CompanyOrganization org,HttpServletRequest request) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		int count = orgService.saveOrUpdate(org);
		if(count == 0){
			jsonObj.setResult(false);
			jsonObj.setData("服务器繁忙,稍后请重试!");
		}else{
			jsonObj.setData("账户保存成功!");
		}
        return jsonObj;
    }
}
