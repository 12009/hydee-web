package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 菜单按钮实体
 */
public class CompanyBtn implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long btnId;

    private Long menuId;//菜单ID

    private String btnName;//按钮名

    private String btnCode;//按钮编码

    private String btnUrl;//按钮URL地址

    private String btnType;//按钮类别

    public Long getBtnId() {
        return btnId;
    }

    public void setBtnId(Long btnId) {
        this.btnId = btnId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName == null ? null : btnName.trim();
    }

    public String getBtnCode() {
        return btnCode;
    }

    public void setBtnCode(String btnCode) {
        this.btnCode = btnCode == null ? null : btnCode.trim();
    }

    public String getBtnUrl() {
        return btnUrl;
    }

    public void setBtnUrl(String btnUrl) {
        this.btnUrl = btnUrl == null ? null : btnUrl.trim();
    }

    public String getBtnType() {
        return btnType;
    }

    public void setBtnType(String btnType) {
        this.btnType = btnType == null ? null : btnType.trim();
    }
}