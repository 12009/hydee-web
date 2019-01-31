package com.hydee.hdsec.dao.impl;

import com.hydee.hdsec.dao.CompanyTrainExercisesDao;
import com.hydee.hdsec.dao.base.BaseDao;
import com.hydee.hdsec.entity.CompanyTrainErrorOption;
import com.hydee.hdsec.entity.CompanyTrainExercises;
import com.hydee.hdsec.entity.CompanyTrainExercisesOptions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    public int deleteExercisesByBaseId(Long baseId) {
        return (Integer) delete("companyTrainExercisesMapper.deleteExercisesByBaseId",baseId);
    }

    @Override
    public int deleteExercisesOptionsByExercisesId(Long exercisesId) {
        return (Integer) delete("companyTrainExercisesMapper.deleteExercisesOptionsByExercisesId",exercisesId);
    }

    @Override
    public int deleteExercises(Long exercisesId) {
        return (Integer) delete("companyTrainExercisesMapper.deleteExercises",exercisesId);
    }

    @Override
    public int deleteExercisesOptionsByBaseId(Long baseId) {
        return (Integer) delete("companyTrainExercisesMapper.deleteExercisesOptionsByBaseId",baseId);
    }

    @Override
    public int deleteExercisesOptionsByClassId(Long classId) {
        return (Integer) delete("companyTrainExercisesMapper.deleteExercisesOptionsByClassId",classId);
    }

    @Override
    public int getExercisesByClassTypeId(Long classTypeId) {
        return (Integer) findForObject("companyTrainExercisesMapper.getExercisesByClassTypeId",classTypeId);
    }

    @Override
    public List<CompanyTrainExercises> selectByClassId(Long classId) {
        return (List<CompanyTrainExercises>) findForList("companyTrainExercisesMapper.selectByClassId",classId);
    }

    @Override
    public List<CompanyTrainExercises> selectByBaseId(Long baseId) {
        return (List<CompanyTrainExercises>) findForList("companyTrainExercisesMapper.selectByBaseId",baseId);
    }

    @Override
    public List<CompanyTrainExercises> selectAllListPage(CompanyTrainExercises companyTrainExercises) {
        return (List<CompanyTrainExercises>) findForList("companyTrainExercisesMapper.selectAllListPage",companyTrainExercises);
    }

    @Override
    public List<CompanyTrainExercises> selectRandomExercises(Map<String, Object> map) {
        return (List<CompanyTrainExercises>) findForList("companyTrainExercisesMapper.selectRandomExercises",map);
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

    @Override
    public List<CompanyTrainExercises> selectHistoryExercises(CompanyTrainErrorOption trainErrorOption) {
        return (List<CompanyTrainExercises>) findForList("companyTrainExercisesMapper.selectHistoryExercises",trainErrorOption);
    }
}
