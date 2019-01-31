package com.hydee.hdsec.dao.impl;

import com.hydee.hdsec.dao.CompanyTrainClassDao;
import com.hydee.hdsec.dao.base.BaseDao;
import com.hydee.hdsec.entity.CompanyTrainClass;
import com.hydee.hdsec.entity.CompanyTrainClassExercises;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by King.Liu
 * 2016/8/23.
 */
@Repository("companyTrainClassDao")
public class CompanyTrainClassDaoImpl extends BaseDao implements CompanyTrainClassDao {
    @Override
    public CompanyTrainClass selectByClassId(Long classId) {
        return (CompanyTrainClass) findForObject("companyTrainClassMapper.selectByClassId",classId);
    }

    @Override
    public List<CompanyTrainClass> queryClassListPage(CompanyTrainClass companyTrainClass) {
        return (List<CompanyTrainClass>) findForList("companyTrainClassMapper.queryClassListPage",companyTrainClass);
    }

    @Override
    public List<CompanyTrainClass> queryClassAll(CompanyTrainClass companyTrainClass) {
        return (List<CompanyTrainClass>) findForList("companyTrainClassMapper.queryClassAll",companyTrainClass);
    }

    @Override
    public Integer insertClassExercises(CompanyTrainClassExercises companyTrainClassExercises) {
        return (Integer) save("companyTrainClassMapper.insertClassExercises",companyTrainClassExercises);
    }

    @Override
    public Integer insertClass(CompanyTrainClass companyTrainClass) {
        return (Integer) save("companyTrainClassMapper.insertClass",companyTrainClass);
    }

    @Override
    public int updateClass(CompanyTrainClass companyTrainClass) {
        return (Integer) update("companyTrainClassMapper.updateClass",companyTrainClass);
    }

    @Override
    public int deleteClassExercisesByClassId(Long classId) {
        return (Integer) delete("companyTrainClassMapper.deleteClassExercisesByClassId",classId);
    }

    @Override
    public int deleteByClassId(Long classId) {
        return (Integer) delete("companyTrainClassMapper.deleteByClassId",classId);
    }
}
