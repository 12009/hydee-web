package com.hydee.hdsec.controller;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.service.CompanyTrainCLassService;
import com.hydee.hdsec.service.CompanyTrainExercisesService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.util.LimitUtil;
import com.hydee.hdsec.vo.JsonVo;
import com.sun.xml.internal.rngom.parse.host.Base;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by King.Liu
 * 2016/8/24.
 */
@Controller
@RequestMapping(value = "/view/trainClass")
public class TrainClassController extends BaseController{
    /**新增培训课件**/
    private static final String ADD_TRAIN_CLASS = "/system/myTraining/addNewTraining";

    /**
     * 跳转到培训课件新增页面
     * @param sType 1、修改，2、重新发布
     * @param classId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addTrainClass")
    public String toViewAddTrainClass(String sType,Long classId,Model model, HttpServletRequest request) throws Exception{
        if(null != sType){
            List<CompanyTrainExercises> exercisesList = companyTrainExercisesService.selectByClassId(classId);
            CompanyTrainClass companyTrainClass = companyTrainCLassService.selectByClassId(classId);
            List<CompanyTrainExercises> exercisess=new ArrayList<CompanyTrainExercises>();
            for (CompanyTrainExercises trainExercises : exercisesList) {
                exercisess=getOptions(exercisesList,trainExercises.getExercisesId());
            }
            model.addAttribute("exercisesList", exercisess);
            model.addAttribute("companyTrainClass", companyTrainClass);
        }
        model.addAttribute("limitCount", LimitUtil.exercisesLimitCount);
        return ADD_TRAIN_CLASS;
    }

    /**
     * 新增或修改
     * @param companyTrainClass
     * @param exercises
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdateClass")
    @ResponseBody
    public Object saveOrUpdateClass(CompanyTrainClass companyTrainClass,String exercises, Model model, HttpServletRequest request) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<String> jsonObj = new JsonVo<String>();
        companyTrainClass.setCreateId(user.getId());
        companyTrainClass.setCustomerId(user.getCustomerId());

        JSONArray jsonArray = JSONArray.fromObject(exercises);
        if(jsonArray.size() <= 0){
            throw new ServiceException(ErrorCodes.CLASS_NOT_EXERCISES);
        }
        if(jsonArray.size() > Integer.parseInt(LimitUtil.exercisesLimitCount)){
            throw new ServiceException(ErrorCodes.CLASS_EXERCISES_SUM);
        }else{
            List<CompanyTrainExercises> companyTrainExercisesList = new ArrayList<CompanyTrainExercises>();
            for (int i=0;i<jsonArray.size();i++){
                CompanyTrainExercises companyTrainExercises = new CompanyTrainExercises();
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                String title = (String) jsonObject.get("title");
                String tabType = (String) jsonObject.get("tabType");
//                String tabA = (String) jsonObject.get("tabA");
//                String tabB = (String) jsonObject.get("tabB");
//                String tabC = (String) jsonObject.get("tabC");
//                String tabD = (String) jsonObject.get("tabD");
                String rightAnswer = (String) jsonObject.get("rightAnswer");
                JSONArray options = JSONArray.fromObject(jsonObject.get("options"));

                List<Option> optionList = new ArrayList<Option>();
                for (int j = 0; j < options.size(); j++) {
                    Option option = new Option();
                    JSONObject optionJson = (JSONObject) options.get(j);
                    String name = (String) optionJson.get("name");
                    String content = (String) optionJson.get("content");
                    option.setName(name);
                    option.setContent(content);
                    optionList.add(option);
                }
                companyTrainExercises.setAnswer(rightAnswer);
                companyTrainExercises.setType(tabType);
                companyTrainExercises.setContent(title);
                companyTrainExercises.setCreateId(user.getId());

//                Map<String,Object> option=new HashMap<String, Object>();
//                option.put("A",tabA);
//                option.put("B",tabB);
//                option.put("C",tabC);
//                option.put("D",tabD);
//                option.put("rightAnswer",rightAnswer);
                companyTrainExercises.setOptions(optionList);

                companyTrainExercisesList.add(companyTrainExercises);
            }
            int count = companyTrainCLassService.saveOrUpdateClass(companyTrainClass,companyTrainExercisesList);

            if(count != 0){
                jsonObj.setResult(false);
                jsonObj.setData("保存失败！");
            }
        }

        return jsonObj;
    }

    /**
     * 修改状态
     * @param companyTrainClass
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateClass")
    @ResponseBody
    public Object updateClass(CompanyTrainClass companyTrainClass,Model model, HttpServletRequest request) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<String> jsonObj = new JsonVo<String>();
        int count = companyTrainCLassService.updateClass(companyTrainClass);
        if(count == 0){
            jsonObj.setResult(false);
            jsonObj.setData("操作失败！");
        }
        return jsonObj;
    }

    /**
     * 显示详情
     * @param classId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showClassDetail")
    @ResponseBody
    public Object showClassDetail(Long classId, Model model, HttpServletRequest request) throws Exception{
        Map<String,Object> map=new HashMap<String,Object>();
        List<CompanyTrainExercises> exercisesList = companyTrainExercisesService.selectByClassId(classId);
        CompanyTrainClass companyTrainClass = companyTrainCLassService.selectByClassId(classId);
        List<CompanyTrainExercises> exercisess = new ArrayList<CompanyTrainExercises>();
        for (CompanyTrainExercises trainExercises : exercisesList) {
            exercisess=getOptions(exercisesList,trainExercises.getExercisesId());
        }
        map.put("exercisesList", exercisess);
        map.put("companyTrainClass", companyTrainClass);
        return map;
    }


    /**
     * 删除课件
     * @param classId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "deleteClass")
    @ResponseBody
    public Object deleteClass(Long classId, Model model, HttpServletRequest request) throws Exception{

        JsonVo<String> jsonObj = new JsonVo<String>();
        List<CompanyTrainTask> trainTasks = companyTrainTaskService.selectTaskByClassId(classId);
//        for (CompanyTrainTask trainTask : trainTasks) {
            if(trainTasks.size() > 0){
                throw new ServiceException(ErrorCodes.TASK_NOW_OPEN);
            }
//        }
        int count = companyTrainCLassService.deleteClass(classId);
        if(count == 0){
            jsonObj.setResult(false);
            jsonObj.setData("操作失败！");
        }
        return jsonObj;
    }


}
