package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 缩略图表实体
 */
public class CompanyTrainTaskThumbnail implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String imgUrl;//图片地址

    private Long taskId;//所属任务

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}