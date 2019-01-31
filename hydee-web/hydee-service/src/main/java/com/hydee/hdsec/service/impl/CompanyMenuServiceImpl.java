package com.hydee.hdsec.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hydee.hdsec.dao.CompanyMenuDao;
import com.hydee.hdsec.entity.CompanyMenu;
import com.hydee.hdsec.service.CompanyMenuService;

@Service
public class CompanyMenuServiceImpl implements CompanyMenuService {
	
	@Resource
	public CompanyMenuDao companyMenuDao;
	
	@Override
	public List<CompanyMenu> listMenuesByRoleId(long roleId) throws Exception {
		// 查询所有菜单
		return companyMenuDao.listByRoleId(roleId);
	}

	@Override
	public List<CompanyMenu> resetMenuesForPage(List<CompanyMenu> list) throws Exception {
		List<CompanyMenu> _list = new ArrayList<CompanyMenu>();
		/* 
		 * 重新封装菜单(将子菜单封装至上级菜单中)
		 * 由于数据库查询已按父-子级菜单进行了排序故不再二次排序,后续若修改SQL请同步此处代码
		 **/
		Map<Long,CompanyMenu> reList = new HashMap<Long,CompanyMenu>();
		for(CompanyMenu menu : list){
			if(menu.isParentMenu()){
				CompanyMenu _menu = menu.clone();
				_list.add(_menu);
				reList.put(menu.getMenuId(),_menu);
			}else{
				CompanyMenu _pMenu = reList.get(menu.getMenuPid());
				if(_pMenu != null) _pMenu.getSubMenues().add(menu);
			}
		}
		return _list;
	}

}
