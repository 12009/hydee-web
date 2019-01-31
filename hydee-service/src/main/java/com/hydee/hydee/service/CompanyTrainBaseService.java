package com.hydee.hydee.service;

import com.hydee.hdsec.entity.CompanyTrainBase;
import com.hydee.hdsec.entity.CompanyTrainClassBase;
import com.hydee.hdsec.entity.CompanyTrainExercises;

import java.util.List;
import java.util.Map;

/**
 * Created by King.Liu
 * 2016/12/8.
 */
public interface CompanyTrainBaseService {
    List<CompanyTrainBase> selectBaseListPage(CompanyTrainBase companyTrainBase) throws Exception;

    List<CompanyTrainBase> selectBaseAll(CompanyTrainBase companyTrainBase) throws Exception;

    CompanyTrainBase selectBaseById(Long baseId) throws Exception;

    CompanyTrainBase selectBaseByClassId(Long classId) throws Exception;

    int saveOrUpdateBase(CompanyTrainBase companyTrainBase,  String exercises) throws Exception;

    int saveOrUpdateSingleExercises(CompanyTrainExercises trainExercises,  String options) throws Exception;

    int issueBase(CompanyTrainBase companyTrainBase) throws Exception;

    int updateBase(CompanyTrainBase companyTrainBase) throws Exception;

    int deleteExercisesBase(Long baseId) throws Exception;

    CompanyTrainClassBase selectBaseClassByClassId(Long classId) throws Exception;
}
