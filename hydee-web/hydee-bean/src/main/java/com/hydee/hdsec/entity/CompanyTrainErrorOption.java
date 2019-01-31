package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 错误题类
 */
public class CompanyTrainErrorOption implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long exercisesId;//所属习题

    private String chooseNo;//选择的答案

    private String customerId;//所属公司

    private String userId;//用户ID

    private Long taskId;//所属任务

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExercisesId() {
        return exercisesId;
    }

    public void setExercisesId(Long exercisesId) {
        this.exercisesId = exercisesId;
    }

    public String getChooseNo() {
        return chooseNo;
    }

    public void setChooseNo(String chooseNo) {
        this.chooseNo = chooseNo == null ? null : chooseNo.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}