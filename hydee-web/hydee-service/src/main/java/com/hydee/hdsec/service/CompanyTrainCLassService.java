package com.hydee.hdsec.service;

import com.hydee.hdsec.entity.CompanyTrainClass;
import com.hydee.hdsec.entity.CompanyTrainClassExercises;
import com.hydee.hdsec.entity.CompanyTrainExercises;
import com.hydee.hdsec.entity.CompanyTrainExercisesOptions;

import java.util.List;

/**
 * Created by King.Liu
 * 2016/8/23.
 */
public interface CompanyTrainCLassService {


    /**
     * 分页查询课件
     * @param companyTrainClass
     * @return
     * @throws Exception
     */
    public List<CompanyTrainClass> queryClassListPage(CompanyTrainClass companyTrainClass) throws Exception;


    /**
     * 查询课件（无分页）
     * @param companyTrainClass
     * @return
     * @throws Exception
     */
    public List<CompanyTrainClass> queryClassAll(CompanyTrainClass companyTrainClass) throws Exception;

    /**
     * 保存或修改
     * @param companyTrainClass
     * @param companyTrainExercisesList
     * @return
     * @throws Exception
     */
    public int saveOrUpdateClass(CompanyTrainClass companyTrainClass, List<CompanyTrainExercises> companyTrainExercisesList) throws Exception;

    /**
     * 修改课件
     * @param companyTrainClass
     * @return
     * @throws Exception
     */
    public int updateClass(CompanyTrainClass companyTrainClass) throws Exception;

    /**
     * 根据课件ID查询课件
     * @param classId
     * @return
     * @throws Exception
     */
    public CompanyTrainClass selectByClassId(Long classId) throws Exception;

    /**
     * 删除课件
     * @param classId
     * @return
     * @throws Exception
     */
    public int deleteClass(Long classId) throws Exception;

    /**
     * 新增阅读量
     * @param trainClass
     * @return
     */
    public int addReadNum(CompanyTrainClass trainClass);
}
