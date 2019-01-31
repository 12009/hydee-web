package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.CompanyTrainClass;
import com.hydee.hdsec.entity.CompanyTrainClassExercises;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 课件持久层
 * Created by King.Liu
 * 2016/8/23.
 */
public interface CompanyTrainClassDao {

    public CompanyTrainClass selectByClassId(Long classId);

    /**
     * 分页查询课件
     * @param companyTrainClass
     * @return
     */
    public List<CompanyTrainClass> queryClassListPage(CompanyTrainClass companyTrainClass);

    public List<CompanyTrainClass> queryClassAll(CompanyTrainClass companyTrainClass);


    /**
     * 新增课件与习题关联表
     * @param companyTrainClassExercises
     * @return
     */
    public Integer insertClassExercises(CompanyTrainClassExercises companyTrainClassExercises);


    /**
     * 新增课件
     * @param companyTrainClass
     * @return
     */
    public Integer insertClass(CompanyTrainClass companyTrainClass);

    /**
     * 修改课件
     * @param companyTrainClass
     * @return
     */
    public int updateClass(CompanyTrainClass companyTrainClass);

    /**
     * 根据课件ID 删除课件习题关联数据
     * @param classId
     * @return
     */
    public int deleteClassExercisesByClassId(Long classId);

    /**
     * 删除课件
     * @param classId
     * @return
     */
    public int deleteByClassId(Long classId);


}
