package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.CompanyTrainExercisesType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyTrainExercisesTypeDao {
    int deleteTypeById(Long classTypeId);

    int insertExercisesType(CompanyTrainExercisesType record);

    CompanyTrainExercisesType selectTypeById(Long classTypeId);

    CompanyTrainExercisesType selectTypeByName(CompanyTrainExercisesType companyTrainExercisesType);

    List<CompanyTrainExercisesType> selectTypeListPage(CompanyTrainExercisesType companyTrainExercisesType);

    List<CompanyTrainExercisesType> selectTypeByCustomerId(CompanyTrainExercisesType companyTrainExercisesType);

    int updateTypeById(CompanyTrainExercisesType record);

    Integer getExercisesType(CompanyTrainExercisesType record);

}