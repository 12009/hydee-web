package com.hydee.hdsec.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.CompanyMenu;
import com.hydee.hdsec.entity.CompanyRole;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hdsec.service.CompanyMenuService;
import com.hydee.hdsec.service.CompanyRoleService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.vo.JsonVo;

@Controller
@RequestMapping(value = "/view/roleMan")
public class RoleManController {
	
	@Autowired
    CompanyRoleService roleService;
	@Autowired
    CompanyMenuService menuService;
	
	@RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(CompanyRole role,HttpServletRequest request) throws Exception{
		CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		JsonVo<String> jsonObj = new JsonVo<String>();
		role.setCreateId(user.getId());
		role.setModifiedId(user.getId());
		int count = roleService.saveOrUpdate(role);
		// 保存角色菜单
		if(role.getMenuIds() != null){
			roleService.saveMenues(role);
		}
		if(count == 0){
			jsonObj.setResult(false);
			jsonObj.setData("服务器繁忙,稍后请重试!");
		}
        return jsonObj;
    }
	
	@RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Long roleId,HttpServletRequest request) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		int useCount = roleService.countUser(roleId);
		if(useCount > 0){
			throw new ServiceException(ErrorCodes.ROLE_HAS_USEFUL);
		}
		int delCount = roleService.deleteByRoleId(roleId);
		if(delCount == 0){
			jsonObj.setResult(false);
		}
        return jsonObj;
    }
	
	@RequestMapping(value = "/listMenues")
    @ResponseBody
    public Object listMenues(Long roleId,HttpServletRequest request) throws Exception{
		JsonVo<List<CompanyMenu>> jsonObj = new JsonVo<List<CompanyMenu>>();
		List<CompanyMenu> menuList = menuService.listMenuesByRoleId(roleId);
		jsonObj.setData(menuList == null ? new ArrayList<CompanyMenu>() : menuList);
        return jsonObj;
    }
}
