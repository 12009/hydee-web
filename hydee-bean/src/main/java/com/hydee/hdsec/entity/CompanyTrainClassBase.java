package com.hydee.hdsec.entity;

import java.io.Serializable;

public class CompanyTrainClassBase implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long classId;

    private Long baseId;

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

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }
}