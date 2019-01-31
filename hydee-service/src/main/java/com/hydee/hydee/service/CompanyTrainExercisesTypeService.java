package com.hydee.hydee.service;

import com.hydee.hdsec.entity.CompanyTrainExercisesType;

import java.util.List;

/**
 * Created by King.Liu
 * 2016/12/8.
 */
public interface CompanyTrainExercisesTypeService {
    List<CompanyTrainExercisesType> selectTypeListPage(CompanyTrainExercisesType companyTrainExercisesType) throws Exception;

    List<CompanyTrainExercisesType> selectTypeByCustomerId(CompanyTrainExercisesType companyTrainExercisesType) throws Exception;

    public int saveOrUpdateExercisesType(CompanyTrainExercisesType exercisesType) throws Exception;

    CompanyTrainExercisesType selectTypeById(Long classTypeId) throws Exception;

    int deleteExercisesType(Long classTypeId) throws Exception;
}
