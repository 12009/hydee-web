package com.hydee.hdsec.service.impl;

import com.hydee.hdsec.dao.CompanyTrainExercisesDao;
import com.hydee.hdsec.entity.CompanyTrainErrorOption;
import com.hydee.hdsec.entity.CompanyTrainExercises;
import com.hydee.hdsec.entity.CompanyTrainExercisesOptions;
import com.hydee.hdsec.service.CompanyTrainExercisesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by King.Liu
 * 2016/8/25.
 */
@Service
public class CompanyTrainExercisesServiceImpl implements CompanyTrainExercisesService {
    @Resource(name = "companyTrainExercisesDao")
    CompanyTrainExercisesDao companyTrainExercisesDao;
    @Override
    public List<CompanyTrainExercises> selectByClassId(Long classId) throws Exception {
        return companyTrainExercisesDao.selectByClassId(classId);
    }

    @Override
    public List<CompanyTrainExercisesOptions> selectOptionsByExercisesId(Long exercisesId) throws Exception {
        return companyTrainExercisesDao.selectOptionsByExercisesId(exercisesId);
    }

    @Override
    public int insertErrorOption(List<CompanyTrainErrorOption> trainErrorOption) throws Exception {
        return companyTrainExercisesDao.insertErrorOption(trainErrorOption);
    }

    @Override
    public List<CompanyTrainErrorOption> selectErrorOption(CompanyTrainErrorOption trainErrorOption) throws Exception {
        return companyTrainExercisesDao.selectErrorOption(trainErrorOption);
    }
}
