package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 培训课件和习题关联 实体
 */
public class CompanyTrainClassExercises implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long classId;

    private Long exercisesId;

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

    public Long getExercisesId() {
        return exercisesId;
    }

    public void setExercisesId(Long exercisesId) {
        this.exercisesId = exercisesId;
    }
}