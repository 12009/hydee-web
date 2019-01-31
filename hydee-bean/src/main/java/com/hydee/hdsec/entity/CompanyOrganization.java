package com.hydee.hdsec.entity;

import java.io.Serializable;

public class CompanyOrganization implements Serializable {
    private static final long serialVersionUID = 1L;
    private String customerId;//厂商ID

    private String orgName;//厂商名称

    private String orgImg;//厂商头像

    private String orgPaperOne;//公司三证一

    private String orgPaperTwo;//公司三证二

    private String orgPaperThree;//公司三证三

    private String address;//公司地址

    private String phone;//公司电话

    private String orgUrl;//公司网址

    private Double serveCharge;//平台服务费

    private Double serveChargePercent;//平台服务费百分比

    private String xmCustomerId;//小蜜连锁ID

    private String xmBindUserId;//小蜜绑定用户

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgImg() {
        return orgImg;
    }

    public void setOrgImg(String orgImg) {
        this.orgImg = orgImg == null ? null : orgImg.trim();
    }

    public String getOrgPaperOne() {
        return orgPaperOne;
    }

    public void setOrgPaperOne(String orgPaperOne) {
        this.orgPaperOne = orgPaperOne == null ? null : orgPaperOne.trim();
    }

    public String getOrgPaperTwo() {
        return orgPaperTwo;
    }

    public void setOrgPaperTwo(String orgPaperTwo) {
        this.orgPaperTwo = orgPaperTwo == null ? null : orgPaperTwo.trim();
    }

    public String getOrgPaperThree() {
        return orgPaperThree;
    }

    public void setOrgPaperThree(String orgPaperThree) {
        this.orgPaperThree = orgPaperThree == null ? null : orgPaperThree.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOrgUrl() {
        return orgUrl;
    }

    public void setOrgUrl(String orgUrl) {
        this.orgUrl = orgUrl == null ? null : orgUrl.trim();
    }

    public Double getServeCharge() {
        return serveCharge;
    }

    public void setServeCharge(Double serveCharge) {
        this.serveCharge = serveCharge;
    }

    public Double getServeChargePercent() {
        return serveChargePercent;
    }

    public void setServeChargePercent(Double serveChargePercent) {
        this.serveChargePercent = serveChargePercent;
    }

    public String getXmCustomerId() {
        return xmCustomerId;
    }

    public void setXmCustomerId(String xmCustomerId) {
        this.xmCustomerId = xmCustomerId;
    }

    public String getXmBindUserId() {
        return xmBindUserId;
    }

    public void setXmBindUserId(String xmBindUserId) {
        this.xmBindUserId = xmBindUserId;
    }
}