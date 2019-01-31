package com.hydee.hdsec.controller;

import com.alibaba.fastjson.JSONObject;
import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.CompanyTrainBase;
import com.hydee.hdsec.entity.CompanyTrainExercises;
import com.hydee.hdsec.entity.CompanyTrainExercisesType;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hydee.service.CompanyTrainExercisesService;
import com.hydee.hydee.service.CompanyTrainExercisesTypeService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.util.EnumUtil;
import com.hydee.hdsec.util.LimitUtil;
import com.hydee.hdsec.vo.JsonVo;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by King.Liu
 * 2016/8/24.
 */
@Controller
@RequestMapping(value = "/view/trainingExercises")
public class TrainExercisesController extends BaseController{
    /**新增常规习题**/
    private static final String ADD_REGULAR_TRAIN_EXERCISES = "/system/myTraining/addRegularExercise";
    /**习题分类**/
    private static final String EXERCISES_CLASS_TYPE = "/system/myTraining/exercisesClassType";
    /**题库列表**/
    private static final String EXERCISES_QUESTION_BANK = "/system/myTraining/exercisesQuestionBank";
    /**新增单题**/
    private static final String SINGLE_ITEM_ADDITION = "/system/myTraining/singleItemAddition";

    @Autowired
    CompanyTrainExercisesTypeService companyTrainExercisesTypeService;
    @Autowired
    public CompanyTrainExercisesService companyTrainExercisesService;

    /**
     * 跳转新增常规习题
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addRegularExercise")
    public String addRegularExercise(Long baseId,Model model, HttpServletRequest request) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        CompanyTrainExercisesType exercisesType = new CompanyTrainExercisesType();
        exercisesType.setCustomerId(user.getCustomerId());
        List<CompanyTrainExercisesType> trainExercisesTypes = companyTrainExercisesTypeService.selectTypeByCustomerId(exercisesType);
        if(null != baseId){
            CompanyTrainBase companyTrainBase = companyTrainBaseService.selectBaseById(baseId);
            List<CompanyTrainExercises> trainExercisesList = companyTrainExercisesService.selectByBaseId(baseId);
            model.addAttribute("companyTrainBase", companyTrainBase);
            model.addAttribute("trainExercisesList", trainExercisesList);
        }
        model.addAttribute("exercisesTypes", trainExercisesTypes);
        model.addAttribute("limitCount", LimitUtil.exercisesLimitCount);
        return ADD_REGULAR_TRAIN_EXERCISES;
    }


    /**
     * 跳转单体添加
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/singleItemAddition")
    public String singleItemAddition(Long exercisesId, Model model, HttpServletRequest request) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        CompanyTrainExercisesType exercisesType = new CompanyTrainExercisesType();
        exercisesType.setCustomerId(user.getCustomerId());
        List<CompanyTrainExercisesType> trainExercisesTypes = companyTrainExercisesTypeService.selectTypeByCustomerId(exercisesType);
        if(null != exercisesId){
            CompanyTrainExercises trainExercises = companyTrainExercisesService.selectByExercisesId(exercisesId);
            model.addAttribute("trainExercises", JSONObject.toJSON(trainExercises));
        }
        model.addAttribute("exercisesTypes", trainExercisesTypes);
        return SINGLE_ITEM_ADDITION;
    }

    /**
     * 保存或修改单个习题
     * @param trainExercises
     * @param option
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdateSingleExercises")
    @ResponseBody
    public Object saveOrUpdateSingleExercises(CompanyTrainExercises trainExercises, String option, Model model, HttpServletRequest request) throws Exception {
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<String> jsonObj = new JsonVo<String>();
        trainExercises.setCustomerId(user.getCustomerId());
        trainExercises.setCreateId(user.getId());
        int count = companyTrainBaseService.saveOrUpdateSingleExercises(trainExercises, option);
        if (count != 0) {
            jsonObj.setResult(false);
            jsonObj.setData("服务器繁忙,稍后请重试!");
        }
        return jsonObj;
    }

    /**
     * 查看常规习题
     * @param baseId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showDetailBase")
    @ResponseBody
    public Object showDetailBase(Long baseId, Model model, HttpServletRequest request) throws Exception {
        JsonVo<Map<String,Object>> jsonObj = new JsonVo<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        CompanyTrainBase companyTrainBase = companyTrainBaseService.selectBaseById(baseId);
        List<CompanyTrainExercises> trainExercisesList = companyTrainExercisesService.selectByBaseId(baseId);
        map.put("companyTrainBase", companyTrainBase);
        map.put("trainExercisesList", trainExercisesList);
        jsonObj.setData(map);
        return jsonObj;
    }

    /**
     * 查看习题库单题详情
     * @param exerciseId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showDetailExercise")
    @ResponseBody
    public Object showDetailExercise(Long exerciseId, Model model, HttpServletRequest request) throws Exception {
        JsonVo<Map<String,Object>> jsonObj = new JsonVo<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        CompanyTrainExercises trainExercises = companyTrainExercisesService.selectByExercisesId(exerciseId);
        map.put("trainExercises", trainExercises);
        jsonObj.setData(map);
        return jsonObj;
    }

    /**
     * 跳转修改随机习题
     * @param baseId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateRandomBase")
    @ResponseBody
    public Object updateRandomBase(Long baseId, Model model, HttpServletRequest request) throws Exception {
        JsonVo<CompanyTrainBase> jsonObj = new JsonVo<CompanyTrainBase>();
        CompanyTrainBase companyTrainBase = companyTrainBaseService.selectBaseById(baseId);
        jsonObj.setData(companyTrainBase);
        return jsonObj;
    }

    /**
     * 保存或修改常规习题
     * @param companyTrainBase
     * @param exercises
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdateBase")
    @ResponseBody
    public Object saveOrUpdateBase(CompanyTrainBase companyTrainBase, String exercises, Model model, HttpServletRequest request) throws Exception {
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<String> jsonObj = new JsonVo<String>();
        companyTrainBase.setCustomerId(user.getCustomerId());
        companyTrainBase.setCreateId(user.getId());
        if(companyTrainBase.getType().equals(EnumUtil.ExercisesBaseType.RANDOM.value)){
            Map<String,Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("customerId",user.getCustomerId());
            paramsMap.put("classTypeId",companyTrainBase.getClassTypeId());
            List<CompanyTrainExercises> trainExercisesList = companyTrainExercisesService.selectRandomExercises(paramsMap);
            if(trainExercisesList.size() < companyTrainBase.getCount()){
                throw new ServiceException(ErrorCodes.EXERCISE_COUNT_NO);
            }
        }
        int count = companyTrainBaseService.saveOrUpdateBase(companyTrainBase, exercises);
        if (count != 0) {
            jsonObj.setResult(false);
            jsonObj.setData("服务器繁忙,稍后请重试!");
        }
        return jsonObj;
    }

    /**
     * 发布习题
     * @param companyTrainBase
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/issueBase")
    @ResponseBody
    public Object issueBase(CompanyTrainBase companyTrainBase, Model model, HttpServletRequest request) throws Exception {
        JsonVo<String> jsonObj = new JsonVo<String>();
        int count = companyTrainBaseService.issueBase(companyTrainBase);
        if (count == 0) {
            jsonObj.setResult(false);
            jsonObj.setData("服务器繁忙,稍后请重试!");
        }
        return jsonObj;
    }

    /**
     * 习题分类列表
     * @param trainExercisesType
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exercisesClassType")
    public String exercisesClassType(CompanyTrainExercisesType trainExercisesType, Model model, HttpServletRequest request) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        trainExercisesType.setCustomerId(user.getCustomerId());
        List<CompanyTrainExercisesType> exercisesTypes = companyTrainExercisesTypeService.selectTypeListPage(trainExercisesType);
        model.addAttribute("reList", exercisesTypes);
        model.addAttribute("page", trainExercisesType);
        return EXERCISES_CLASS_TYPE;
    }

    /**
     * 习题题库列表
     * @param trainExercises
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exercisesQuestionBank")
    public String exercisesQuestionBank(CompanyTrainExercises trainExercises, Model model, HttpServletRequest request) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        trainExercises.setCustomerId(user.getCustomerId());
        List<CompanyTrainExercises> exercises = companyTrainExercisesService.selectAllListPage(trainExercises);
        CompanyTrainExercisesType exercisesType = new CompanyTrainExercisesType();
        exercisesType.setCustomerId(user.getCustomerId());
        List<CompanyTrainExercisesType> trainExercisesTypes = companyTrainExercisesTypeService.selectTypeByCustomerId(exercisesType);
        model.addAttribute("exercisesTypes", trainExercisesTypes);
        model.addAttribute("reList", exercises);
        model.addAttribute("page", trainExercises);
        return EXERCISES_QUESTION_BANK;
    }

    /**
     * 新增或修改习题分类
     * @param trainExercisesType
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdateExercisesType")
    @ResponseBody
    public Object saveOrUpdateExercisesType(CompanyTrainExercisesType trainExercisesType, Model model, HttpServletRequest request) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<String> jsonObj = new JsonVo<String>();
        trainExercisesType.setCustomerId(user.getCustomerId());
        trainExercisesType.setCreateId(user.getId());
        int count = companyTrainExercisesTypeService.saveOrUpdateExercisesType(trainExercisesType);
        if(count == 0){
            jsonObj.setResult(false);
            jsonObj.setData("服务器繁忙,稍后请重试!");
        }
        return jsonObj;
    }

    /**
     * 删除习题分类
     * @param classTypeId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteExercisesType")
    @ResponseBody
    public Object deleteExercisesType(Long classTypeId, Model model, HttpServletRequest request) throws Exception{
        JsonVo<String> jsonObj = new JsonVo<String>();
        int count = companyTrainExercisesTypeService.deleteExercisesType(classTypeId);
        if(count != 0){
            jsonObj.setResult(false);
            jsonObj.setData("服务器繁忙,稍后请重试!");
        }
        return jsonObj;
    }

    /**
     * 弹出修改，根据ID获取该行的值
     * @param classTypeId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateExercises")
    @ResponseBody
    public Object updateExercises(Long classTypeId, Model model, HttpServletRequest request) throws Exception{
        JsonVo<CompanyTrainExercisesType> jsonObj = new JsonVo<CompanyTrainExercisesType>();
        CompanyTrainExercisesType exercisesType = companyTrainExercisesTypeService.selectTypeById(classTypeId);
        jsonObj.setData(exercisesType);
        return jsonObj;
    }


    /**
     * 删除习题
     * @param baseId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteExercisesBase")
    @ResponseBody
    public Object deleteExercisesBase(Long baseId, Model model, HttpServletRequest request) throws Exception{
        JsonVo<String> jsonObj = new JsonVo<String>();
        int count = companyTrainBaseService.deleteExercisesBase(baseId);
        if(count != 0){
            jsonObj.setResult(false);
            jsonObj.setData("服务器繁忙,稍后请重试!");
        }
        return jsonObj;
    }

    /**
     * 删除单个习题
     * @param exercisesId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteExercises")
    @ResponseBody
    public Object deleteExercises(Long exercisesId, Model model, HttpServletRequest request) throws Exception{
        JsonVo<String> jsonObj = new JsonVo<String>();
        int count = companyTrainExercisesService.deleteExercises(exercisesId);
        if(count != 0){
            jsonObj.setResult(false);
            jsonObj.setData("服务器繁忙,稍后请重试!");
        }
        return jsonObj;
    }


    /**
     * 检测导入的习题
     * @param attach
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/importExercisesListExcel", method = RequestMethod.POST)
    @ResponseBody
    public Object importExercisesListExcel(MultipartFile attach, HttpServletRequest request, HttpServletResponse response) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<Map<String,Object>> result = new JsonVo<Map<String,Object>>();
        Map<String,Object> map = companyTrainExercisesService.exportExercises(attach,user);
        String _uuId = UUID.randomUUID().toString().replace("-", "");
        map.put("sessionId",_uuId);
        request.getSession().setAttribute(_uuId,map.get("trueArray"));
        result.setData(map);
        return result;
    }

    /**
     * 上传审核通过的习题
     * @param sessionId
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveExercisesListExcel", method = RequestMethod.POST)
    @ResponseBody
    public Object saveExercisesListExcel(String sessionId, HttpServletRequest request, HttpServletResponse response) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<String> result = new JsonVo<String>();
        JSONArray jsonArray = (JSONArray) request.getSession().getAttribute(sessionId);
        System.out.println(jsonArray);
        request.getSession().removeAttribute(sessionId);
        int count = companyTrainExercisesService.saveExercisesExcel(jsonArray,user);
        if(count != 0){
            result.setResult(false);
            result.setData("服务器繁忙,稍后请重试!");
        }
        return result;
    }

    @RequestMapping(value = "/deletExercisesList", method = RequestMethod.POST)
    @ResponseBody
    public Object deletExercisesList(@RequestParam(required = false, value = "exercisesIds[]") List<Long> exercisesIds, HttpServletRequest request, HttpServletResponse response) throws Exception{
        JsonVo<String> jsonVo = new JsonVo<String>();
        String result = companyTrainExercisesService.deleteExercisesAll(exercisesIds);
        jsonVo.setData(result);
        return jsonVo;
    }
}
