package com.hydee.hdsec.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.CompanyMenu;
import com.hydee.hdsec.entity.CompanyOrganization;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hdsec.entity.IPAddress;
import com.hydee.hydee.service.CompanyMenuService;
import com.hydee.hydee.service.CompanyOrgService;
import com.hydee.hydee.service.CompanyUserService;
import com.hydee.hydee.service.OperationLogService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.util.EmailUtils;
import com.hydee.hdsec.util.EnumUtil;
import com.hydee.hdsec.util.HttpClientUtils;
import com.hydee.hdsec.util.MD5;
import com.hydee.hdsec.util.StringUtil;
import com.hydee.hdsec.vo.JsonVo;

/**
 * Created by Administrator on 2016/8/16.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    protected Logger logger = Logger.getLogger(this.getClass());
    
    private static final String LOGIN = "main/login";
    private static final String REGISTER = "main/register";
    private static final String FORGET_PWD_1ST = "main/forgetPwdStep1st";
    private static final String FORGET_PWD_2ND = "main/forgetPwdStep2nd";
    private static final String FORGET_PWD_3RD = "main/forgetPwdStep3rd";

    @Autowired
    OperationLogService operationLogService;

    @Autowired
    CompanyUserService companyUserService;
    
    @Autowired
    CompanyMenuService menuService;
    
    @Autowired
	CompanyOrgService orgService;

    /**
     * 跳转到登陆页面
     *
     * @return 登陆视图
     */
    @RequestMapping("/goLogin")
    public ModelAndView goLogin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(LOGIN);
        return mv;
    }
    
    @RequestMapping("/goRegist")
    public String goRegist() {
        return REGISTER;
    }
    
    @RequestMapping("/forgetPwd/{step}/{username}")
    public String forgetPwd(@PathVariable String step,@PathVariable String username,Model model,HttpServletRequest request) {
    	model.addAttribute("username", username);
        if (step.equals("2nd")) {
            return FORGET_PWD_2ND;
        } else if (step.equals("3rd")) {
            try {
                String verifiCode = request.getParameter("verifiCode");
                if (StringUtil.isBlank(verifiCode)) return FORGET_PWD_2ND;
                if (verifiCode.equals(MD5.md5(companyUserService.getUsefulVerifiCode(username)))) return FORGET_PWD_3RD;
                return FORGET_PWD_2ND;
            } catch (Exception e) {
                return FORGET_PWD_2ND;
            }
        }
        return FORGET_PWD_1ST;
    }
    

    @RequestMapping(value = "/toLogin")
    @ResponseBody
    public Object login(CompanyUser companyUser, HttpServletRequest request) {
        Map<String, Object> map = null;
        try {
            map = new HashMap<String, Object>();
            // 加密用户密码
            companyUser.setUserPassword(MD5.md5(companyUser.getUserPassword()));
            CompanyUser users = companyUserService.selectUserByPassWord(companyUser);
            if(null != users){
                if(EnumUtil.LoginStatus.STATUS_USERFUL.value != users.getStatus()){
                    map.put("loginMsg", "帐号未启用！");
                    map.put("loginErrMsg", "err");
                    map.put("result","erro");
                }else{
                    users.setYdjToken(UUID.randomUUID().toString().replace("-", ""));
                    request.getSession().setAttribute(Const.SESSIONCODE,users);
                    // 获取用户权限下所开启的菜单列表
                    List<CompanyMenu> menues = menuService.listMenuesByRoleId(users.getRoleId());
                    request.getSession().setAttribute(Const.SESSION_MENUES,menues);
                    String visitIp = request.getRemoteAddr();
                    IPAddress ipAddr = operationLogService.countIpAddr(visitIp) == 0 ?
                            HttpClientUtils.requestIpAddr(visitIp) : null;
                    Date nowDate = new Date();
                    users.setLastLoginTime(nowDate);

                    if(ipAddr != null && !StringUtil.isBlank(ipAddr.getIp())){
                        // 插入IP地址
                        operationLogService.insertIpAddr(ipAddr);
                        users.setLoginIp(ipAddr.getIp());
                    }
                    companyUserService.updateByCompanyUser(users);
                    map.put("users",users);
                    map.put("zllnKey",MD5.getZllnKey());
                    map.put("result","success");
                }
            }else{
                map.put("loginMsg", "帐号或密码不正确！");
                map.put("loginErrMsg", "err");
                map.put("result","erro");
            }
        } catch (Exception e) {
            logger.error("登陆失败",e);
            map.put("loginMsg", "服务器繁忙，稍后请重试！");
            map.put("loginErrMsg", "err");
            map.put("result","erro");
        }
        return map;
    }
    
    @RequestMapping(value = "/doReigist")
    @ResponseBody
    public Object doReigist(CompanyUser companyUser, HttpServletRequest request) throws Exception {
    	JsonVo<String> jsonObj = new JsonVo<String>();
    	if(companyUserService.findUserByUsername(companyUser) != null){
    		// 用户名已存在
    		throw new ServiceException(ErrorCodes.USERNAME_HAS_REGISTED);
    	}
    	// 构建机构对象
		CompanyOrganization org = new CompanyOrganization(); 
		org.setCustomerId(companyUser.getCustomerId());
		org.setOrgName(companyUser.getCompany());
		// 判断机构名是否已使用
		if(orgService.findOrgByOrgname(org) != null){
			throw new ServiceException(ErrorCodes.ORG_NAME_HAS_REPEAT);
		}
    	int addCount = companyUserService.registUser(companyUser);
    	if(addCount < 1){
    		jsonObj.setResult(false);
    		jsonObj.setData("注册失败,稍后请重试!");
    	}else{
    		jsonObj.setData("注册成功,等待管理员审核!");
    	}
    	return jsonObj;
    }
    
    @RequestMapping(value = "/resetPassword/{step}")
    @ResponseBody
    public Object resetPassword(@PathVariable String step,CompanyUser companyUser) throws Exception{
    	JsonVo<Object> jsonObj = new JsonVo<Object>();
    	if(step.equals("1st")){
    		CompanyUser _companyUser = companyUserService.findeUserByUsernameAndEmail(companyUser.getUserName(),companyUser.getEmail());
    		if(_companyUser == null){
    			// 用户名与邮箱不匹配
    			throw new ServiceException(ErrorCodes.EMAIL_USERNAME_NOT_MATCH);
    		}
    		// 生成验证码
    		String verifiCode = StringUtil.getVerifiCode(6);
    		int addCount = companyUserService.updateVerifyCode(_companyUser.getId(), verifiCode);
    		if(addCount == 0){
    			jsonObj.setResult(false);
    			jsonObj.setData("验证码生成失败,稍后请重试!");
    		}else{
    			EmailUtils.sendVerifiCode(companyUser.getEmail(), verifiCode, companyUser.getUserName());
    			jsonObj.setData(companyUser.getUserName());
    		}
    	}else if(step.equals("2nd")){
    		String verifiCode = companyUserService.getUsefulVerifiCode(companyUser.getUserName());
    		if(StringUtil.isBlank(verifiCode)){
    			jsonObj.setResult(false);
    			jsonObj.setData("验证码已超时,请重新获取新的验证码!");
    		}else if(verifiCode.equals(companyUser.getVerifiCode())){
    			Map<String,String> params = new HashMap<String,String>();
    			params.put("userName", companyUser.getUserName());
    			params.put("verifiCode", MD5.md5(verifiCode));
    			jsonObj.setData(params);
    		}else{
    			jsonObj.setResult(false);
    			jsonObj.setData("验证码错误,请核对您的验证码!");
    		}
    	}else if(step.equals("3rd")){
    		// 重置验证码,让其无效
    		companyUser.setVerifiCode("");
    		// 重加密用户明文密码
    		companyUser.setUserPassword(MD5.md5(companyUser.getUserPassword()));
    		int upCount = companyUserService.updateByCompanyUser(companyUser);
    		if(upCount == 0){
    			jsonObj.setResult(false);
    			jsonObj.setData("重置密码失败,请核对您的信息是否填写正确!");
    		}
    	}
    	return jsonObj;
    }

    /**
     * 用户注销
     * @return
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView();
    	try{
    		CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
    		user.setYdjToken("");
    		companyUserService.updateByCompanyUser(user);
    		request.getSession(true).removeAttribute(Const.SESSIONCODE);
    	}catch(Exception ex){
    		logger.error("", ex);
    	}
    	mv.setViewName(LOGIN);
        return mv;

    }

}
