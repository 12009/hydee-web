package com.hydee.hydee.service.impl;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.CompanyTrainExercisesDao;
import com.hydee.hdsec.dao.CompanyTrainExercisesTypeDao;
import com.hydee.hdsec.entity.CompanyTrainExercisesType;
import com.hydee.hydee.service.CompanyTrainExercisesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by King.Liu
 * 2016/12/8.
 */
@Service
public class CompanyTrainExercisesTypeServiceImpl implements CompanyTrainExercisesTypeService {
    @Autowired
    CompanyTrainExercisesTypeDao companyTrainExercisesTypeDao;
    @Autowired
    CompanyTrainExercisesDao companyTrainExercisesDao;
    @Override
    public List<CompanyTrainExercisesType> selectTypeListPage(CompanyTrainExercisesType companyTrainExercisesType) throws Exception {
        return companyTrainExercisesTypeDao.selectTypeListPage(companyTrainExercisesType);
    }

    @Override
    public List<CompanyTrainExercisesType> selectTypeByCustomerId(CompanyTrainExercisesType companyTrainExercisesType) throws Exception {
        return companyTrainExercisesTypeDao.selectTypeByCustomerId(companyTrainExercisesType);
    }

    @Override
    public int saveOrUpdateExercisesType(CompanyTrainExercisesType exercisesType) throws Exception{
        int count = 0;
        if(null == exercisesType.getClassTypeId()){
            if(null != companyTrainExercisesTypeDao.selectTypeByName(exercisesType))
                throw new ServiceException(ErrorCodes.EXERCISE_TYPE_NAME_ERROR);
            count = companyTrainExercisesTypeDao.insertExercisesType(exercisesType);
        }else{
            if(0 < companyTrainExercisesTypeDao.getExercisesType(exercisesType))
                throw new ServiceException(ErrorCodes.EXERCISE_TYPE_NAME_ERROR);
            count = companyTrainExercisesTypeDao.updateTypeById(exercisesType);
        }
        return count;
    }

    @Override
    public CompanyTrainExercisesType selectTypeById(Long classTypeId) throws Exception {
        return companyTrainExercisesTypeDao.selectTypeById(classTypeId);
    }

    @Override
    public int deleteExercisesType(Long classTypeId) throws Exception {
        int count = companyTrainExercisesDao.getExercisesByClassTypeId(classTypeId);
        if(count > 0){
            throw new ServiceException(ErrorCodes.EXERCISE_TYPE_NOW);
        }
        companyTrainExercisesTypeDao.deleteTypeById(classTypeId);
        return 0;
    }
}
