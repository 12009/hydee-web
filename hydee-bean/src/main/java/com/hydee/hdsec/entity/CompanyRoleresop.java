package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 角色菜单实体
 */
public class CompanyRoleresop implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long menuId;//菜单id

    private Long roleId;//角色ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}