package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 账户总额实体
 */
public class CompanyAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String customerId;//所属公司

    private Double availableBalance;//可用余额

    private int sType;//新增或减少余额1、新增；2、减少

    private Double balance;//余额

    private String dayTaskCount;//汇总30天每天参加任务人数

    public CompanyAccount(){
        this.dayTaskCount = "";
    }

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

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public int getsType() {
        return sType;
    }

    public void setsType(int sType) {
        this.sType = sType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getDayTaskCount() {
        return dayTaskCount;
    }

    public void setDayTaskCount(String dayTaskCount) {
        this.dayTaskCount = dayTaskCount;
    }
}