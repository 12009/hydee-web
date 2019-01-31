package com.hydee.hdsec.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 连锁企业账户表
 */
public class CompanyCustomerAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String customerId;//连锁ID

    private Double balance;//余额

    private String customerName;//连锁名称

    private Date createTime;//创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}