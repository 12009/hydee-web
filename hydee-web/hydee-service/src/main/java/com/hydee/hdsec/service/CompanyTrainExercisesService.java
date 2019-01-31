package com.hydee.hdsec.service;

import com.hydee.hdsec.entity.CompanyTrainErrorOption;
import com.hydee.hdsec.entity.CompanyTrainExercises;
import com.hydee.hdsec.entity.CompanyTrainExercisesOptions;

import java.util.List;

/**
 * Created by King.Liu
 * 2016/8/25.
 */
public interface CompanyTrainExercisesService {

    public List<CompanyTrainExercises> selectByClassId(Long classId) throws Exception;

    public List<CompanyTrainExercisesOptions> selectOptionsByExercisesId(Long exercisesId) throws Exception;

    public int insertErrorOption(List<CompanyTrainErrorOption> trainErrorOptions) throws Exception;

    public List<CompanyTrainErrorOption> selectErrorOption(CompanyTrainErrorOption trainErrorOption) throws Exception;
}
