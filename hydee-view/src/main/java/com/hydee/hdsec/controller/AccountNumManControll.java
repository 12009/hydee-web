package com.hydee.hdsec.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hydee.service.CompanyUserService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.util.EnumUtil;
import com.hydee.hdsec.util.MD5;
import com.hydee.hdsec.vo.JsonVo;

@Controller
@RequestMapping(value = "/view/accountNumMan")
public class AccountNumManControll {
	
	@Autowired
    CompanyUserService userService;
	
	@RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(CompanyUser user,HttpServletRequest request) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		CompanyUser _user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		// 判断用户名是否已使用
		if(userService.findUserByUsername(user) != null){
			throw new ServiceException(ErrorCodes.USERNAME_HAS_REGISTED);
		}
		// 保存用户信息
		user.setModifiedId(_user.getId());
		if(user.getId() == null){
			user.setStatus(EnumUtil.LoginStatus.STATUS_USERFUL.value);
			user.setCreateId(_user.getId());
		}
		int count = userService.saveOrUpdate(user);
		if(count == 0){
			jsonObj.setResult(false);
			jsonObj.setData("服务器繁忙,稍后请重试!");
		}else{
			jsonObj.setData("账户保存成功!");
		}
        return jsonObj;
    }
	
	@RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail(Long userId,HttpServletRequest request) throws Exception{
		JsonVo<CompanyUser> jsonObj = new JsonVo<CompanyUser>();
		CompanyUser user = userService.findUserById(userId);
		jsonObj.setData(user);
        return jsonObj;
    }
	
	@RequestMapping(value = "/updateStatus")
    @ResponseBody
    public Object updateStatus(Long userId,int status,HttpServletRequest request) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		CompanyUser companyUser = new CompanyUser();
		companyUser.setId(userId);
		companyUser.setStatus(status);
		companyUser.setModifiedId(user.getId());
		int count = userService.updateByCompanyUser(companyUser);
		if(count == 0){
			jsonObj.setResult(false);
			jsonObj.setData("服务器繁忙,稍后请重试");
		}
        return jsonObj;
    }
	/**
	 * 重置账户密码
	 * @param userId
	 * @param userPassword
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetPassword")
    @ResponseBody
    public Object resetPassword(Long userId,String userPassword,HttpServletRequest request) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		CompanyUser companyUser = new CompanyUser();
		companyUser.setId(userId);
		companyUser.setUserPassword(MD5.md5(userPassword));
		companyUser.setModifiedId(user.getId());
		int count = userService.updateByCompanyUser(companyUser);
		if(count == 0){
			jsonObj.setResult(false);
			jsonObj.setData("服务器繁忙,稍后请重试");
		}
        return jsonObj;
    }

	/**
	 * 重置账户密码
	 * @param userOldPassWord
	 * @param userNewPassWord
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changePassWord")
	@ResponseBody
	public Object changePassWord(String userOldPassWord,String userNewPassWord,HttpServletRequest request) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		if(!user.getUserPassword().equals(MD5.md5(userOldPassWord))){
			throw new ServiceException(ErrorCodes.OLD_PASS_WORD_ERROR);
		}
		CompanyUser companyUser = new CompanyUser();
		companyUser.setId(user.getId());
		companyUser.setUserPassword(MD5.md5(userNewPassWord));
		companyUser.setModifiedId(user.getId());
		int count = userService.updateByCompanyUser(companyUser);
		if(count == 0){
			jsonObj.setResult(false);
			jsonObj.setData("服务器繁忙,稍后请重试");
		}else{
			request.getSession(true).removeAttribute(Const.SESSIONCODE);
		}
		return jsonObj;
	}
}
