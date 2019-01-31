package com.hydee.hydee.service.impl;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.CompanyTrainBaseDao;
import com.hydee.hdsec.dao.CompanyTrainExercisesDao;
import com.hydee.hdsec.entity.*;
import com.hydee.hydee.service.CompanyTrainBaseService;
import com.hydee.hdsec.util.EnumUtil;
import com.hydee.hdsec.util.LimitUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyTrainBaseServiceImpl implements CompanyTrainBaseService {
    @Autowired
    CompanyTrainBaseDao companyTrainBaseDao;
    @Autowired
    CompanyTrainExercisesDao companyTrainExercisesDao;

    @Override
    public List<CompanyTrainBase> selectBaseListPage(CompanyTrainBase companyTrainBase) throws Exception {
        return companyTrainBaseDao.selectBaseListPage(companyTrainBase);
    }

    @Override
    public List<CompanyTrainBase> selectBaseAll(CompanyTrainBase companyTrainBase) throws Exception {
        return companyTrainBaseDao.selectBaseAll(companyTrainBase);
    }

    @Override
    public CompanyTrainBase selectBaseById(Long baseId) throws Exception {
        return companyTrainBaseDao.selectBaseById(baseId);
    }

    @Override
    public CompanyTrainBase selectBaseByClassId(Long classId) throws Exception {
        return companyTrainBaseDao.selectBaseByClassId(classId);
    }

    @Override
    public int saveOrUpdateBase(CompanyTrainBase companyTrainBase, String exercises) throws Exception {
        Long baseId = 0L;
        companyTrainBase.setStatus(0);
        JSONArray jsonArray = new JSONArray();
        if(companyTrainBase.getType().equals(EnumUtil.ExercisesBaseType.GROOVE.value)){
            jsonArray = JSONArray.fromObject(exercises);
            companyTrainBase.setCount(jsonArray.size());//更新习题数量
        }
        if(null == companyTrainBase.getBaseId()){
            companyTrainBaseDao.insertBase(companyTrainBase);
            baseId = companyTrainBase.getBaseId();
        }else{
            baseId = companyTrainBase.getBaseId();
            companyTrainBaseDao.updateBase(companyTrainBase);
            if(companyTrainBase.getType().equals(EnumUtil.ExercisesBaseType.GROOVE.value)){
//                companyTrainExercisesDao.deleteExercisesOptionsByBaseId(companyTrainBase.getBaseId());
                companyTrainExercisesDao.deleteExercisesByBaseId(companyTrainBase.getBaseId());//逻辑删除
                companyTrainBaseDao.deleteBaseExercises(companyTrainBase.getBaseId());
            }
        }
        if(companyTrainBase.getType().equals(EnumUtil.ExercisesBaseType.GROOVE.value)){//常规习题对应新增习题ID
            if (jsonArray.size() <= 0) {
                throw new ServiceException(ErrorCodes.CLASS_NOT_EXERCISES);
            }
            if (jsonArray.size() > Integer.parseInt(LimitUtil.exercisesLimitCount)) {
                throw new ServiceException(ErrorCodes.CLASS_EXERCISES_SUM);
            } else {
                for (int i = 0; i < jsonArray.size(); i++) {
                    CompanyTrainExercises companyTrainExercises = new CompanyTrainExercises();
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    String title = (String) jsonObject.get("title");
                    String tabType = (String) jsonObject.get("tabType");
                    String rightAnswer = (String) jsonObject.get("rightAnswer");
                    JSONArray options = JSONArray.fromObject(jsonObject.get("options"));
                    companyTrainExercises.setAnswer(rightAnswer);
                    companyTrainExercises.setType(tabType);
                    companyTrainExercises.setContent(title);
                    companyTrainExercises.setCreateId(companyTrainBase.getCreateId());
                    companyTrainExercises.setClassTypeId(companyTrainBase.getClassTypeId());
                    companyTrainExercises.setCustomerId(companyTrainBase.getCustomerId());
                    companyTrainExercisesDao.insertExercises(companyTrainExercises);
                    Long exercisesId = companyTrainExercises.getExercisesId();
                    CompanyTrainBaseExercises companyTrainBaseExercises=new CompanyTrainBaseExercises();
                    companyTrainBaseExercises.setBaseId(baseId);
                    companyTrainBaseExercises.setExercisesId(exercisesId);
                    companyTrainBaseDao.insertBaseExercises(companyTrainBaseExercises);

                    for (int j = 0; j < options.size(); j++) {
                        JSONObject optionJson = (JSONObject) options.get(j);
                        String name = (String) optionJson.get("name");
                        String content = (String) optionJson.get("content");
                        if (null == content) {
                            break;
                        }
                        CompanyTrainExercisesOptions companyTrainExercisesOptions = new CompanyTrainExercisesOptions();
                        companyTrainExercisesOptions.setContent(content);
                        companyTrainExercisesOptions.setOptionNo(name);
                        companyTrainExercisesOptions.setExercisesId(exercisesId);
                        String answer = companyTrainExercises.getAnswer();
                        int isAnswer = 0;
                        char[] chs = answer.toCharArray();
                        for(Character ch:chs){
                            if(name.equals(ch.toString())){
                                isAnswer = 1;
                                break;
                            }
                        }
                        companyTrainExercisesOptions.setIsAnswer(isAnswer);
                        companyTrainExercisesDao.insertExercisesOptions(companyTrainExercisesOptions);
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public int saveOrUpdateSingleExercises(CompanyTrainExercises trainExercises, String options) throws Exception {
        JSONArray jsonArray = JSONArray.fromObject(options);
        Long exercisesId = 0L;
        if(null == trainExercises.getExercisesId()){
            companyTrainExercisesDao.insertExercises(trainExercises);
            exercisesId = trainExercises.getExercisesId();
        }else{
//            companyTrainBaseDao.deleteExercisesById(exercisesId);
            exercisesId = trainExercises.getExercisesId();
            companyTrainExercisesDao.deleteExercisesOptionsByExercisesId(exercisesId);
            companyTrainExercisesDao.updateByExercises(trainExercises);
        }
        for (int j = 0; j < jsonArray.size(); j++) {
            JSONObject optionJson = (JSONObject) jsonArray.get(j);
            String name = (String) optionJson.get("name");
            String content = (String) optionJson.get("content");
            if (null == content || "".equals(content)) {
                break;
            }
            CompanyTrainExercisesOptions companyTrainExercisesOptions = new CompanyTrainExercisesOptions();
            companyTrainExercisesOptions.setContent(content);
            companyTrainExercisesOptions.setOptionNo(name);
            companyTrainExercisesOptions.setExercisesId(exercisesId);
            String answer = trainExercises.getAnswer();
            int isAnswer = 0;
            char[] chs = answer.toCharArray();
            for(Character ch:chs){
                if(name.equals(ch.toString())){
                    isAnswer = 1;
                    break;
                }
            }
            companyTrainExercisesOptions.setIsAnswer(isAnswer);
            companyTrainExercisesDao.insertExercisesOptions(companyTrainExercisesOptions);
        }

        return 0;
    }

    @Override
    public int issueBase(CompanyTrainBase companyTrainBase) throws Exception {
        return companyTrainBaseDao.updateBase(companyTrainBase);
    }

    @Override
    public int updateBase(CompanyTrainBase companyTrainBase) throws Exception {
        return companyTrainBaseDao.updateBase(companyTrainBase);
    }

    @Override
    public int deleteExercisesBase(Long baseId) throws Exception {

        int count = companyTrainBaseDao.selectBaseClassByBaseId(baseId);
        if(count > 0){
            throw new ServiceException(ErrorCodes.BASE_CLASS_NOW);
        }
        companyTrainExercisesDao.deleteExercisesByBaseId(baseId);//逻辑删除
        companyTrainBaseDao.deleteBaseExercises(baseId);
        companyTrainBaseDao.deleteBaseById(baseId);
        return 0;
    }

    @Override
    public CompanyTrainClassBase selectBaseClassByClassId(Long classId) throws Exception {
        return companyTrainBaseDao.selectBaseClassByClassId(classId);
    }

}
