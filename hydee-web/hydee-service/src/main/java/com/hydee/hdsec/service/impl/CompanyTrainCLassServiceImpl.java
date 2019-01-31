package com.hydee.hdsec.service.impl;

import com.hydee.hdsec.dao.CompanyTrainClassDao;
import com.hydee.hdsec.dao.CompanyTrainExercisesDao;
import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.service.CompanyTrainCLassService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<CompanyTrainClass> queryClassListPage(CompanyTrainClass companyTrainClass) throws Exception {
        return companyTrainClassDao.queryClassListPage(companyTrainClass);
    }

    @Override
    public List<CompanyTrainClass> queryClassAll(CompanyTrainClass companyTrainClass) throws Exception {
        return companyTrainClassDao.queryClassAll(companyTrainClass);
    }

    @Override
    public int saveOrUpdateClass(CompanyTrainClass companyTrainClass, List<CompanyTrainExercises> companyTrainExercisesList) throws Exception{
        int flag = 0;
        Long classId = 0L;
        if(null == companyTrainClass.getClassId()){
            companyTrainClass.setStatus(1);
            companyTrainClassDao.insertClass(companyTrainClass);
            classId = companyTrainClass.getClassId();
        }else{
            companyTrainClass.setStatus(1);
            classId = companyTrainClass.getClassId();
            companyTrainClassDao.updateClass(companyTrainClass);
            companyTrainExercisesDao.deleteExercisesByClassId(classId);
            companyTrainClassDao.deleteClassExercisesByClassId(classId);
        }
        for (CompanyTrainExercises trainExercises : companyTrainExercisesList) {
            companyTrainExercisesDao.insertExercises(trainExercises);
            Long exercisesId = trainExercises.getExercisesId();
            CompanyTrainClassExercises companyTrainClassExercises=new CompanyTrainClassExercises();
            companyTrainClassExercises.setClassId(classId);
            companyTrainClassExercises.setExercisesId(exercisesId);
            companyTrainClassDao.insertClassExercises(companyTrainClassExercises);
            List<Option> options=trainExercises.getOptions();
            for (Option option : options) {
                CompanyTrainExercisesOptions companyTrainExercisesOptions=new CompanyTrainExercisesOptions();
                companyTrainExercisesOptions.setContent((String) option.getContent());
                companyTrainExercisesOptions.setOptionNo((String) option.getName());
                companyTrainExercisesOptions.setExercisesId(exercisesId);
                String answer = trainExercises.getAnswer();
                int isAnswer = 0;
                char[] chs = answer.toCharArray();
                for(Character ch:chs){
                    if(option.getName().equals(ch.toString())){
                        isAnswer = 1;
                        break;
                    }
                }
                companyTrainExercisesOptions.setIsAnswer(isAnswer);
                companyTrainExercisesDao.insertExercisesOptions(companyTrainExercisesOptions);
            }
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
        companyTrainExercisesDao.deleteExercisesOptionsByClassId(classId);
        companyTrainExercisesDao.deleteExercisesByClassId(classId);
        companyTrainClassDao.deleteClassExercisesByClassId(classId);
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
