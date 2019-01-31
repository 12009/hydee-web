package com.hydee.hdsec.entity;

import com.hydee.hdsec.entity.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class CompanyTrainExercisesType extends Page {
    private static final long serialVersionUID = 1L;
    private Long classTypeId;

    private String name;

    private Long createId;

    private Date createTime;

    private String customerId;

    private String realName;

    public Long getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(Long classTypeId) {
        this.classTypeId = classTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeFmt() {
        return DateUtil.format(createTime);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}