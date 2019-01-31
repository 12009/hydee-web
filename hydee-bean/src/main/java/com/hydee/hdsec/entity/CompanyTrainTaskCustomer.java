package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 指定连锁实体
 */
public class CompanyTrainTaskCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String customerId;//连锁ID

    private String customerName;//连锁名称

    private Long taskId;//所属任务

    private Integer isParticipation;//是否参与(0:未参与、1:参与)

    private Integer isActivation;//是否激活(0:未激活、1:激活)

    private Integer isStores;//是否为门店（0：连锁、1：门店）

    private String busno;
    private String busName;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getIsParticipation() {
        return isParticipation;
    }

    public void setIsParticipation(Integer isParticipation) {
        this.isParticipation = isParticipation;
    }

    public Integer getIsActivation() {
        return isActivation;
    }

    public void setIsActivation(Integer isActivation) {
        this.isActivation = isActivation;
    }

    public Integer getIsStores() {
        return isStores;
    }

    public void setIsStores(Integer isStores) {
        this.isStores = isStores;
    }

    public String getBusno() {
        return busno;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }
}