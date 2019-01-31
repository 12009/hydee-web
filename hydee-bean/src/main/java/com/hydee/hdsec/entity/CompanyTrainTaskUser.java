package com.hydee.hdsec.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 参与任务人数
 */
public class CompanyTrainTaskUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String userId;//用户ID

    private String userName;//用户名称

    private String customerId;//所属公司

    private Long taskId;//任务ID

    private Date createTime;//参加时间

    private Double redMoney;//红包金额

    private String phone;//联系电话

    private Double correct;//正确率

    private String customerName;//所属公司名

    private String chainCustomerId;//连锁企业

    private Integer type;//参加人类型(1、任务结束后参加练习；0、正常参加活动)

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getRedMoney() {
        return redMoney;
    }

    public void setRedMoney(Double redMoney) {
        this.redMoney = redMoney;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Double getCorrect() {
        return correct;
    }

    public void setCorrect(Double correct) {
        this.correct = correct;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getChainCustomerId() {
        return chainCustomerId;
    }

    public void setChainCustomerId(String chainCustomerId) {
        this.chainCustomerId = chainCustomerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}