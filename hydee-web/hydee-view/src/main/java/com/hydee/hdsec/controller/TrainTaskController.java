package com.hydee.hdsec.controller;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.entity.utils.DateUtil;
import com.hydee.hdsec.service.*;
import com.hydee.hdsec.util.*;
import com.hydee.hdsec.vo.JsonVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by King.Liu
 * 2016/8/24.
 */
@Controller
@RequestMapping(value = "/view/trainTask")
public class TrainTaskController extends BaseController{
    /**新增培训任务**/
    private static final String ADD_TRAIN_TASK = "/system/myTraining/addNewTrainingTask";

    private static final String COMPANY_SPACE = "messageCompany/showAllCompany";

    @Autowired
    public CompanyTrainTaskService companyTrainTaskService;
    @Autowired
    public CompanyTrainCLassService companyTrainCLassService;
    @Autowired
    public CompanyTrainExercisesService companyTrainExercisesService;
    @Autowired
    public CompanyAccountRechargeService companyAccountRechargeService;
    @Autowired
    public CompanyAccountService companyAccountService;
    @Autowired
    public CompanyOrgService companyOrgService;

    /**
     * 跳转到培训任务新增页面
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addTrainTask")
    public String toViewAddTrainTask(String type,Long taskId, Model model, HttpServletRequest request) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        if(null != type){
            CompanyTrainTask companyTrainTask = companyTrainTaskService.selectTaskById(taskId);
            CompanyTrainClassTask companyTrainClassTask = companyTrainTaskService.selectClassTaskByTaskId(taskId);
            List<CompanyTrainTaskThumbnail> taskThumbnailList = companyTrainTaskService.selectThumbnailByTaskId(taskId);
            List<CompanyTrainTaskCustomer> taskCustomerList = companyTrainTaskService.selectCustomerByTaskId(taskId);
            model.addAttribute("companyTrainTask",companyTrainTask);
            model.addAttribute("companyTrainClassTask",companyTrainClassTask);
            model.addAttribute("taskThumbnailList",JSONArray.fromObject(taskThumbnailList));
            model.addAttribute("taskCustomerList",JSONArray.fromObject(taskCustomerList));
        }
        String URL_ASSIGN = "http://"+HttpUtil.IP+":"+HttpUtil.PORT+"/"+HttpUtil.NAMESPACE+"/"+COMPANY_SPACE;
        CompanyTrainClass companyTrainClass = new CompanyTrainClass();
        companyTrainClass.setCustomerId(user.getCustomerId());
        companyTrainClass.setStatus(3);//查询进行中的课件
        List<CompanyTrainClass> companyTrainClassList = companyTrainCLassService.queryClassAll(companyTrainClass);
        CompanyAccount companyAccount = companyAccountService.selectByCustomerId(user.getCustomerId());
        CompanyOrganization companyOrganization = companyOrgService.findByCustomerId(user.getCustomerId());
        model.addAttribute("companyTrainClassList",companyTrainClassList);
        model.addAttribute("availableBalance",companyAccount == null ? 0 : companyAccount.getAvailableBalance());
        model.addAttribute("companyUrl",URL_ASSIGN);
        model.addAttribute("type",type);
        model.addAttribute("serveCharge",companyOrganization.getServeCharge() == null ? ServerChargeUtil.SERVERCHARGE : companyOrganization.getServeCharge());
        model.addAttribute("serveChargePercent",companyOrganization.getServeChargePercent() == null ? ServerChargeUtil.SERVERCHARGEPERCENT : companyOrganization.getServeChargePercent());
        return ADD_TRAIN_TASK;
    }

    /**
     * 查看任务详情
     * @param taskId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showTaskDetail")
    @ResponseBody
    public Object showTaskDetail(Long taskId, Model model, HttpServletRequest request) throws Exception{
        Map<String,Object> map = getTaskDetail(taskId);
        return map;
    }


    /**
     * 修改任务或新增
     * @param companyTrainTask
     * @param classId
     * @param thumbnails
     * @param customer
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdateTask")
    @ResponseBody
    public Object saveOrUpdateTask(CompanyTrainTask companyTrainTask, Long classId,String thumbnails,String customer, Model model, HttpServletRequest request) throws Exception{
        if(null == classId || "".equals(customer)){
            throw new ServiceException(ErrorCodes.TASK_PARAMS);
        }
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<String> jsonObj = new JsonVo<String>();
        companyTrainTask.setCreateId(user.getId());
        companyTrainTask.setCustomerId(user.getCustomerId());
        int flag = companyTrainTaskService.saveOrUpdateTask(companyTrainTask,classId,thumbnails,customer,user);

        if(0 != flag){
            jsonObj.setResult(false);
            jsonObj.setData("保存失败！");
        }
        return jsonObj;
    }

    /**
     * 发布任务
     * @param companyTrainTask
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/issueTask")
    @ResponseBody
    public Object issueTask(CompanyTrainTask companyTrainTask,Model model, HttpServletRequest request) throws Exception{
        if(null == companyTrainTask.getTaskId()){
            throw new ServiceException(ErrorCodes.TASK_ID_NOT_NULL);
        }
//        int status = companyTrainTask.getStatus();
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<String> jsonObj = new JsonVo<String>();
        companyTrainTask.setModifiedId(user.getId());
        int flag = companyTrainTaskService.issueTask(companyTrainTask,user);

        if(0 != flag){
            jsonObj.setResult(false);
            jsonObj.setData("操作失败！");
            return jsonObj;
        }
        return jsonObj;
    }

    /**
     * 删除任务
     * @param taskId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteTask")
    @ResponseBody
    public Object deleteTask(Long taskId,Model model, HttpServletRequest request) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<String> jsonObj = new JsonVo<String>();
        CompanyTrainTask companyTrainTask = companyTrainTaskService.selectTaskById(taskId);
        if(null != companyTrainTask){
            if(3 == companyTrainTask.getStatus() || 4 == companyTrainTask.getStatus()){
                throw new ServiceException(ErrorCodes.TASK_STATUS);
            }
        }else{
            throw new ServiceException(ErrorCodes.TASK_NOT_EXIST);
        }

        int flag = companyTrainTaskService.deleteTask(taskId,user);
        if(0 != flag){
            jsonObj.setResult(false);
            jsonObj.setData("保存失败！");
        }
        return jsonObj;
    }

    /**
     * 增加奖金显示余额
     * @param taskId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showAddMoney")
    @ResponseBody
    public Object showAddMoney(Long taskId, Model model, HttpServletRequest request) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        CompanyAccount companyAccount = companyAccountRechargeService.selectByCustomerId(user.getCustomerId());
        CompanyTrainTask companyTrainTask = companyTrainTaskService.selectTaskById(taskId);
        map.put("companyAccount",companyAccount);
        map.put("companyTrainTask",companyTrainTask);
        return map;
    }

    @RequestMapping(value = "/addMoney")
    @ResponseBody
    public Object addMoney(CompanyTrainTask companyTrainTask, Model model, HttpServletRequest request) throws Exception{
        CompanyUser _user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        JsonVo<String> jsonObj = new JsonVo<String>();
        CompanyTrainTask trainTask = companyTrainTaskService.selectTaskById(companyTrainTask.getTaskId());
        double addMoney = companyTrainTask.getResidueMoney();
        double moneyReward = trainTask.getMoneyReward();
        double residueMoney = trainTask.getResidueMoney();
        companyTrainTask.setModifiedId(_user.getId());
        companyTrainTask.setResidueMoney(addMoney+residueMoney);
        companyTrainTask.setMoneyReward(moneyReward+addMoney);

        int flag = companyTrainTaskService.addMoney(companyTrainTask,addMoney,_user);
        if(0 != flag){
            jsonObj.setResult(false);
            jsonObj.setData("操作失败！");
            return jsonObj;
        }
        return jsonObj;
    }

}
