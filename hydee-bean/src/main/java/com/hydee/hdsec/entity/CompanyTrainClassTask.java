package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 任务和课件关联表实体
 */
public class CompanyTrainClassTask implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long classId;

    private Long taskId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}