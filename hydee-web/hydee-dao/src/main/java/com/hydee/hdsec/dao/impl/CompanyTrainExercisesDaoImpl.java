package com.hydee.hdsec.dao.impl;

import com.hydee.hdsec.dao.CompanyTrainExercisesDao;
import com.hydee.hdsec.dao.base.BaseDao;
import com.hydee.hdsec.entity.CompanyTrainErrorOption;
import com.hydee.hdsec.entity.CompanyTrainExercises;
import com.hydee.hdsec.entity.CompanyTrainExercisesOptions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by King.Liu
 * 2016/8/24.
 */
@Repository("companyTrainExercisesDao")
public class CompanyTrainExercisesDaoImpl extends BaseDao implements CompanyTrainExercisesDao {
    @Override
    public int deleteExercisesByClassId(Long classId) {
        return (Integer) delete("companyTrainExercisesMapper.deleteExercisesByClassId",classId);
    }

    @Override
    public int deleteExercisesOptionsByClassId(Long classId) {
        return (Integer) delete("companyTrainExercisesMapper.deleteExercisesOptionsByClassId",classId);
    }

    @Override
    public List<CompanyTrainExercises> selectByClassId(Long classId) {
        return (List<CompanyTrainExercises>) findForList("companyTrainExercisesMapper.selectByClassId",classId);
    }

    @Override
    public List<CompanyTrainExercisesOptions> selectOptionsByExercisesId(Long exercisesId) {
        return (List<CompanyTrainExercisesOptions>) findForList("companyTrainExercisesMapper.selectOptionsByExercisesId",exercisesId);
    }

    @Override
    public Integer insertExercises(CompanyTrainExercises companyTrainExercises) {
        return (Integer) save("companyTrainExercisesMapper.insertExercises",companyTrainExercises);
    }

    @Override
    public Integer insertExercisesOptions(CompanyTrainExercisesOptions companyTrainExercisesOptions) {
        return (Integer) save("companyTrainExercisesMapper.insertExercisesOptions",companyTrainExercisesOptions);
    }

    @Override
    public CompanyTrainExercises selectByExercisesId(Long exercisesId) {
        return (CompanyTrainExercises) findForObject("companyTrainExercisesMapper.selectByExercisesId",exercisesId);
    }

    @Override
    public int updateByExercises(CompanyTrainExercises record) {
        return (Integer) update("companyTrainExercisesMapper.updateByExercises",record);
    }

    @Override
    public int insertErrorOption(List<CompanyTrainErrorOption> trainErrorOption) {
        return (Integer) save("companyTrainExercisesMapper.insertErrorOption",trainErrorOption);
    }

    @Override
    public List<CompanyTrainErrorOption> selectErrorOption(CompanyTrainErrorOption trainErrorOption) {
        return (List<CompanyTrainErrorOption>) findForList("companyTrainExercisesMapper.selectErrorOption",trainErrorOption);
    }
}
