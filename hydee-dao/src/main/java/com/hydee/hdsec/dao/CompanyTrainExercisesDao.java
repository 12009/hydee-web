package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.CompanyTrainErrorOption;
import com.hydee.hdsec.entity.CompanyTrainExercises;
import com.hydee.hdsec.entity.CompanyTrainExercisesOptions;

import java.util.List;
import java.util.Map;

/**
 * 培训习题
 */
public interface CompanyTrainExercisesDao {
    /**
     * 删除习题
     * @param classId
     * @return
     */
    int deleteExercisesByClassId(Long classId);

    int deleteExercisesByBaseId(Long baseId);

    int deleteExercisesOptionsByExercisesId(Long exercisesId);

    int deleteExercises(Long exercisesId);

    int deleteExercisesOptionsByBaseId(Long baseId);

    int deleteExercisesOptionsByClassId(Long classId);

    int getExercisesByClassTypeId(Long classTypeId);

    /**
     * 根据课件查询习题
     * @param classId
     * @return
     */
    List<CompanyTrainExercises> selectByClassId(Long classId);

    List<CompanyTrainExercises> selectByBaseId(Long baseId);

    List<CompanyTrainExercises> selectAllListPage(CompanyTrainExercises companyTrainExercises);

    List<CompanyTrainExercises> selectRandomExercises(Map<String,Object> map);

    /**
     * 根据习题ID查询选项
     * @param exercisesId
     * @return
     */
    List<CompanyTrainExercisesOptions> selectOptionsByExercisesId(Long exercisesId);

    /**
     * 批量新增习题
     * @param companyTrainExercises
     * @return
     */
    Integer insertExercises(CompanyTrainExercises companyTrainExercises);

    /**
     * 批量新增习题
     * @param companyTrainExercisesOptions
     * @return
     */
    Integer insertExercisesOptions(CompanyTrainExercisesOptions companyTrainExercisesOptions);

    /**
     * 查询习题
     * @param exercisesId
     * @return
     */
    CompanyTrainExercises selectByExercisesId(Long exercisesId);

    /**
     * 修改习题
     * @param record
     * @return
     */
    int updateByExercises(CompanyTrainExercises record);

    int insertErrorOption(List<CompanyTrainErrorOption> trainErrorOption);

    List<CompanyTrainErrorOption> selectErrorOption(CompanyTrainErrorOption trainErrorOption);

    List<CompanyTrainExercises> selectHistoryExercises(CompanyTrainErrorOption trainErrorOption);

}