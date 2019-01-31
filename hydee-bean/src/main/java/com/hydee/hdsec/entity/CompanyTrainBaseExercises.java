package com.hydee.hdsec.entity;

import java.io.Serializable;

public class CompanyTrainBaseExercises implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long exercisesId;

    private Long baseId;

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

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }
}