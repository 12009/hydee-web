package com.hydee.hdsec.controller;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.*;
import com.hydee.hydee.service.CompanyTrainBaseService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.util.EnumUtil;
import com.hydee.hdsec.vo.JsonVo;
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

    @Autowired
    CompanyTrainBaseService companyTrainBaseService;
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
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        if(null != sType){
            CompanyTrainClass companyTrainClass = companyTrainCLassService.selectByClassId(classId);
            CompanyTrainBase trainBase = companyTrainBaseService.selectBaseByClassId(classId);
            model.addAttribute("companyTrainClass", companyTrainClass);
            model.addAttribute("trainBase", trainBase);
        }
        CompanyTrainBase companyTrainBase = new CompanyTrainBase();
        companyTrainBase.setCustomerId(user.getCustomerId());
        companyTrainBase.setStatus(1);//进行中的
        List<CompanyTrainBase> trainBases = companyTrainBaseService.selectBaseAll(companyTrainBase);
        model.addAttribute("trainBases", trainBases);
        return ADD_TRAIN_CLASS;
    }

    /**
     * 新增或修改
     * @param companyTrainClass
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdateClass")
    @ResponseBody
    public Object saveOrUpdateClass(CompanyTrainClass companyTrainClass, Model model, HttpServletRequest request) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<String> jsonObj = new JsonVo<String>();
        companyTrainClass.setCreateId(user.getId());
        companyTrainClass.setCustomerId(user.getCustomerId());
        int count = companyTrainCLassService.saveOrUpdateClass(companyTrainClass);

        if(count != 0){
            jsonObj.setResult(false);
            jsonObj.setData("保存失败！");
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
        CompanyTrainClass companyTrainClass = companyTrainCLassService.selectByClassId(classId);
        CompanyTrainBase companyTrainBase = companyTrainBaseService.selectBaseById(companyTrainClass.getBaseId());
        if(companyTrainBase.getType().equals(EnumUtil.ExercisesBaseType.GROOVE.value)){
            List<CompanyTrainExercises> trainExercisesList = companyTrainExercisesService.selectByBaseId(companyTrainClass.getBaseId());
            map.put("trainExercisesList", trainExercisesList);
        }
        map.put("companyTrainBase", companyTrainBase);
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
