package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.CompanyTrainBase;
import com.hydee.hdsec.entity.CompanyTrainBaseExercises;
import com.hydee.hdsec.entity.CompanyTrainClassBase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CompanyTrainBaseDao {
    int deleteBaseById(Long baseId);

    int insertBase(CompanyTrainBase record);

    int insertClassBase(CompanyTrainClassBase record);

    CompanyTrainBase selectBaseById(Long baseId);

    CompanyTrainBase selectBaseByClassId(Long classId);

    List<CompanyTrainBase> selectBaseListPage(CompanyTrainBase companyTrainBase);

    List<CompanyTrainBase> selectBaseAll(CompanyTrainBase companyTrainBase);

    int updateBase(CompanyTrainBase record);

    int updateClassBaseByClassId(CompanyTrainClassBase record);

    int insertBaseExercises(CompanyTrainBaseExercises trainBaseExercises);

    int deleteBaseExercises(Long baseId);

    int deleteClassBase(Long classId);

    int deleteExercisesById(Long exercisesId);

    int selectBaseClassByBaseId(Long baseId);

    int selectBaseExercisesByExercisesId(Long exercisesId);

    CompanyTrainClassBase selectBaseClassByClassId(Long classId);
}