package com.hydee.hdsec.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户余额
 */
public class CompanyUserAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String userId;//用户

    private String customerId;//所属连锁

    private Double balance;//余额

    private Date createTime;//创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}