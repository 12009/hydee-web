package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 培训习题选项实体
 */
public class CompanyTrainExercisesOptions implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String content;//内容

    private String optionNo;//编号

    private Long exercisesId;//所属习题

    private int isAnswer;//是否为答案

    private CompanyTrainExercises companyTrainExercises;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getOptionNo() {
        return optionNo;
    }

    public void setOptionNo(String optionNo) {
        this.optionNo = optionNo == null ? null : optionNo.trim();
    }

    public Long getExercisesId() {
        return exercisesId;
    }

    public void setExercisesId(Long exercisesId) {
        this.exercisesId = exercisesId;
    }

    public int getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(int isAnswer) {
        this.isAnswer = isAnswer;
    }

    public CompanyTrainExercises getCompanyTrainExercises() {
        return companyTrainExercises;
    }

    public void setCompanyTrainExercises(CompanyTrainExercises companyTrainExercises) {
        this.companyTrainExercises = companyTrainExercises;
    }
}