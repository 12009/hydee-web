package com.hydee.hdsec.controller;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.service.*;
import com.hydee.hdsec.util.*;
import com.hydee.hdsec.vo.JsonVo;
import com.hydee.hydee.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    CompanyService companyService;
    @Autowired
    MobileUserService mobileUserService;
    @Autowired
    private ShareService shareService;
    /**
     * 封装ERP接口调用参数模型
     * @param model				:response与前端交互模型
     * @param userId				:登录用户
     * @param customerId				:登录用户
     */
    public void resetErpReqModel(Model model, String customerId,String userId) throws Exception{

        Company _company = companyService.getCompanyByCustomerId(customerId);
        String hType = StringUtils.isEmpty(_company.gethType()) ? "H1" : _company.gethType();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("customerId",customerId);
        params.put("userId",userId);
        MobileUser mbUser = mobileUserService.getByMobileUser(params);
        JSONObject jObj = new JSONObject();
        jObj.put("serverUrl", _company.getServerUrl());
        jObj.put("customerId", customerId);
        if(customerId.equals("80B98A10-5AA9-4E90-BE6A-301098E7FA18") || customerId.equals("85607D3A-1C2D-4000-8935-CCB95CA4425A")
                || customerId.equals("86B98A10-5AA9-4E90-BE6A-301098E7FA18") || customerId.equals("88B98A10-5AA9-4E90-BE6A-301098E7FA18")){
            _company.setVerifyCode("0J#=4N''AQHX@<V/IYP`Hbc_L\\]TP`fgOdLWSmKoWlrs[flh_tz{citgrZ`kvrtqc}o|");
        }
        jObj.put("verifycode",_company.getVerifyCode());
        if(hType.equals("H2")){
            jObj.put("userid", mbUser.getUserId());
            jObj.put("password", mbUser.getPassword());
            jObj.put("h2Compid", mbUser.getH2compid());
            jObj.put("compid", mbUser.getH2compid());
        }
        model.addAttribute("hType", hType);
        model.addAttribute("params", jObj.toString());
        model.addAttribute("companyName", _company.getCompanyName());
        model.addAttribute("customerId", customerId);
        if(null==userId && "H2".equals(hType)){
            model.addAttribute("erroType", "1");
        }
    }
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
            if(user.getIsXmCustomer()){
                for (CompanyTrainTaskCustomer taskCustomer: taskCustomerList) {
                    taskCustomer.setBusno(taskCustomer.getCustomerId());
                    taskCustomer.setBusName(taskCustomer.getCustomerName());
                }
            }
            model.addAttribute("taskCustomerList",JSONArray.fromObject(taskCustomerList));
        }
        String URL_ASSIGN = "http://"+HttpUtil.IP+":"+HttpUtil.PORT+"/"+HttpUtil.NAMESPACE;
        CompanyTrainClass companyTrainClass = new CompanyTrainClass();
        companyTrainClass.setCustomerId(user.getCustomerId());
        companyTrainClass.setStatus(3);//查询进行中的课件
        List<CompanyTrainClass> companyTrainClassList = companyTrainCLassService.queryClassAll(companyTrainClass);
        CompanyAccount companyAccount = companyAccountService.selectByCustomerId(user.getCustomerId());
        CompanyOrganization companyOrganization = companyOrgService.findByCustomerId(user.getCustomerId());
        if(user.getIsXmCustomer()){
            resetErpReqModel(model,user.getXmCustomerId(),user.getXmBindUserId());//获取门店需要的参数
        }else{
            JSONObject jObj = new JSONObject();
            jObj.put("serverUrl", "");
            model.addAttribute("params",jObj.toString());
            model.addAttribute("hType","h1");
        }
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
    public Object saveOrUpdateTask(CompanyTrainTask companyTrainTask,String connBusiGroupListJsonStr, Long classId,String thumbnails,String customer, Model model, HttpServletRequest request) throws Exception{
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        if(null == classId || (!user.getIsXmCustomer() && "".equals(customer))){
            throw new ServiceException(ErrorCodes.TASK_PARAMS);
        }
        JsonVo<String> jsonObj = new JsonVo<String>();
        companyTrainTask.setCreateId(user.getId());
        companyTrainTask.setCustomerId(user.getCustomerId());
        int flag = companyTrainTaskService.saveOrUpdateTask(companyTrainTask,connBusiGroupListJsonStr,classId,thumbnails,customer,user);

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

    /**
     * 增加奖金
     * @param companyTrainTask
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
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

    @RequestMapping(value = "/showShares")
    @ResponseBody
    public Object showShares(Share share,HttpServletRequest request) throws Exception {
        share.setSourceType("3");
        List<Share> reList = shareService.listAllShares(share);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("reList",reList);
        map.put("share",share);
        return map;
    }

}
