package com.hydee.hdsec.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 锁定金额
 */
public class CompanyTrainTaskLockMoney implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Double lockMoney;//锁定的金额

    private Date createTime;//创建时间

    private Long taskId;//所属任务

    private String customerId;//所属公司

    private String userId;//用户ID

    private int status;//状态0：删除、1：有效

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLockMoney() {
        return lockMoney;
    }

    public void setLockMoney(Double lockMoney) {
        this.lockMoney = lockMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}