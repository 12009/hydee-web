package com.hydee.hydee.service;

import java.util.List;

import com.hydee.hdsec.entity.CompanyMenu;

public interface CompanyMenuService {
	/**
	 * 查询角色所属的所有菜单
	 * @param roleId	:角色ID
	 * @return
	 * @throws Exception
	 */
	List<CompanyMenu> listMenuesByRoleId(long roleId) throws Exception;
	/**
	 * 重新封装角色所属菜单集合(用于页面加载菜单用)
	 * @param list		:所有菜单集合
	 * @return
	 * @throws Exception
	 */
	List<CompanyMenu> resetMenuesForPage(List<CompanyMenu> list) throws Exception;
}
