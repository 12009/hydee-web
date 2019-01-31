package com.hydee.hydee.service.impl;

import com.hydee.hdsec.dao.CompanyTrainBaseDao;
import com.hydee.hdsec.dao.CompanyTrainClassDao;
import com.hydee.hdsec.dao.CompanyTrainExercisesDao;
import com.hydee.hdsec.entity.CompanyTrainClass;
import com.hydee.hdsec.entity.CompanyTrainClassBase;
import com.hydee.hydee.service.CompanyTrainCLassService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by King.Liu
 * 2016/8/23.
 */
@Service
public class CompanyTrainCLassServiceImpl implements CompanyTrainCLassService {
    protected Logger logger = Logger.getLogger(this.getClass());
    @Resource(name = "companyTrainClassDao")
    CompanyTrainClassDao companyTrainClassDao;
    @Resource(name = "companyTrainExercisesDao")
    CompanyTrainExercisesDao companyTrainExercisesDao;
    @Autowired
    CompanyTrainBaseDao companyTrainBaseDao;

    @Override
    public List<CompanyTrainClass> queryClassListPage(CompanyTrainClass companyTrainClass) throws Exception {
        return companyTrainClassDao.queryClassListPage(companyTrainClass);
    }

    @Override
    public List<CompanyTrainClass> queryClassAll(CompanyTrainClass companyTrainClass) throws Exception {
        return companyTrainClassDao.queryClassAll(companyTrainClass);
    }

    @Override
    public int saveOrUpdateClass(CompanyTrainClass companyTrainClass) throws Exception{
        int flag = 0;
        Long classId = 0L;
        if(null == companyTrainClass.getClassId()){
            companyTrainClass.setStatus(1);
            companyTrainClassDao.insertClass(companyTrainClass);
            classId = companyTrainClass.getClassId();
            CompanyTrainClassBase trainClassBase = new CompanyTrainClassBase();
            trainClassBase.setBaseId(companyTrainClass.getBaseId());
            trainClassBase.setClassId(classId);
            companyTrainBaseDao.insertClassBase(trainClassBase);
        }else{
            companyTrainClass.setStatus(1);
            classId = companyTrainClass.getClassId();
            companyTrainClassDao.updateClass(companyTrainClass);
            CompanyTrainClassBase trainClassBase = new CompanyTrainClassBase();
            trainClassBase.setBaseId(companyTrainClass.getBaseId());
            trainClassBase.setClassId(classId);
            companyTrainBaseDao.updateClassBaseByClassId(trainClassBase);
        }
        return flag;

    }

    @Override
    public int updateClass(CompanyTrainClass companyTrainClass) throws Exception {
        return companyTrainClassDao.updateClass(companyTrainClass);
    }

    @Override
    public CompanyTrainClass selectByClassId(Long classId) throws Exception {
        return companyTrainClassDao.selectByClassId(classId);
    }

    /**
     * 删除课件
     * @param classId
     * @return
     * @throws Exception
     */
    @Override
    public int deleteClass(Long classId) throws Exception {
        companyTrainBaseDao.deleteClassBase(classId);
        companyTrainClassDao.deleteByClassId(classId);
        return 1;
    }

    @Override
    public int addReadNum(CompanyTrainClass trainClass) {
        int read = trainClass.getReadNum();
        read++;
        trainClass.setReadNum(read);
        return companyTrainClassDao.updateClass(trainClass);
    }



    public static void main(String[] args) {
        String answer ="AB";
        char[] chs = answer.toCharArray();
        for(Character ch:chs){
            System.out.println(ch);
        }

    }

}
