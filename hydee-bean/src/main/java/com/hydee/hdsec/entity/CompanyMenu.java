package com.hydee.hdsec.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单实体
 */
public class CompanyMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long menuId;

    private Long menuPid;//父级菜单id

    private String menuCode;//菜单编码

    private String menuName;//菜单名称

    private String menuUrl;//菜单url地址

    private int isShow;//是否管理员显示（1、显示，0、不显示）
    
    private List<CompanyMenu> subMenues = new ArrayList<CompanyMenu>();//子菜单

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(Long menuPid) {
        this.menuPid = menuPid;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = (menuUrl == null ? "" : menuUrl.trim());
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

	public List<CompanyMenu> getSubMenues() {
		return subMenues;
	}

	public void setSubMenues(List<CompanyMenu> subMenues) {
		this.subMenues = subMenues;
	}
	
	public boolean isParentMenu(){
		return this.menuPid < 0;
	}
    /**
     * 克隆菜单(不包含子菜单列表)
     */
	public CompanyMenu clone(){
		CompanyMenu _menu = new CompanyMenu();
		_menu.setMenuId(this.menuId);
		_menu.setMenuCode(this.menuCode);
		_menu.setIsShow(isShow);
		_menu.setMenuCode(menuCode);
		_menu.setMenuName(menuName);
		_menu.setMenuUrl(menuUrl);
		_menu.setMenuPid(menuPid);
		return _menu;
	}
}