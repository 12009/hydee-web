package com.hydee.hdsec.controller.interfaceSec;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.service.MobileUserService;
import com.hydee.hdsec.sqlDao.MessageDao;
import com.hydee.hdsec.util.*;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.controller.BaseController;
import com.hydee.hydee.service.CompanyAccountWhereService;
import com.hydee.hdsec.vo.JsonVo;

/**
 * 任务接口类
 * Created by King.Liu
 * 2016/8/24.
 */
@Controller
@RequestMapping(value = "/trainInterface")
public class TrainInterfaceController extends BaseController{
	
	@Autowired
	private CompanyAccountWhereService accoutWhereService;

    private static final String ADD_COMMENT_POTIONS = "messageCompany/commentAddPoints";

    private static final String ADD_LIKE_POTIONS = "messageCompany/addLike";

    private static Random random = new Random();
    /**
     * 小蜜后台厂商培训任务列表显示
     * @param companyTrainTask(secCustomerId,userId)
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showTaskList")
    @ResponseBody
    public Object showTaskList(CompanyTrainTask companyTrainTask, Model model, HttpServletRequest request) throws Exception{
        if(null == companyTrainTask.getSecCustomerId()){
            throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
        }
        Map<String,Object> map=new HashMap<String,Object>();
        List<CompanyTrainTask> trainTasks = companyTrainTaskService.queryTaskInterfaceListPage(companyTrainTask);
        map.put("allCount", companyTrainTask.getTotalResult());
        map.put("trainTasks",trainTasks);
        return map;
    }

    @RequestMapping(value = "/checkTaskEnd")
    @ResponseBody
    public Object checkTaskEnd(CompanyTrainTask companyTrainTask, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(null == companyTrainTask.getTaskId() || null == companyTrainTask.getSecCustomerId()){
            throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        JsonVo<Map<String,Object>> jsonObj = new JsonVo<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("taskId",companyTrainTask.getTaskId());
        map.put("customerId",companyTrainTask.getSecCustomerId());
        CompanyTrainTask trainTask = companyTrainTaskService.selectTaskByCidAndTaskId(map);
        if (3 != trainTask.getStatus() || 1 != trainTask.getIsActivation()) {//只有进行中的才能抢赏金 错误码 1016
            throw new ServiceException(ErrorCodes.TASK_NOT_DOING);
        }
        return jsonObj;
    }

    /**
     * 小蜜APP厂商培训任务列表显示
     * @param companyTrainTask(secCustomerId,userId)
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showTaskAppList")
    @ResponseBody
    public Object showTaskAppList(CompanyTrainTask companyTrainTask, Model model, HttpServletRequest request) throws Exception{
        if(null == companyTrainTask.getUserId() || null == companyTrainTask.getSecCustomerId()){
            throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
        }
        JsonVo<Map<String,Object>> jsonObj = new JsonVo<Map<String,Object>>();
        Map<String,Object> map=new HashMap<String,Object>();
        List<CompanyTrainTask> trainTasks = companyTrainTaskService.queryTaskAppListPage(companyTrainTask);
        List<CompanyTrainTask> trainTaskList = new ArrayList<CompanyTrainTask>();
        for(CompanyTrainTask task : trainTasks){
            List<CompanyTrainTaskThumbnail> taskThumbnailList = companyTrainTaskService.selectThumbnailByTaskId(task.getTaskId());
            String imgUrl = "";
            if(taskThumbnailList.size() > 0){
                imgUrl = taskThumbnailList.get(0).getImgUrl();
            }
            task.setImgUrl(imgUrl);
            trainTaskList.add(task);
        }

        map.put("trainTasks",trainTaskList);
        jsonObj.setData(map);
        return jsonObj;
    }


    /**
     * 小蜜后台开启和关闭任务
     * @param companyTrainTask
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateTask")
    @ResponseBody
    public Object updateTask(CompanyTrainTask companyTrainTask,Model model, HttpServletRequest request) throws Exception{
        if(null == companyTrainTask.getTaskId()){
            throw new ServiceException(ErrorCodes.TASK_ID_NOT_NULL);
        }
        int status = companyTrainTask.getStatus();
        JsonVo<String> jsonObj = new JsonVo<String>();
//        companyTrainTask.setModifiedId(0L);//小蜜用户修改
//        int flag = companyTrainTaskService.updateTaskById(companyTrainTask);
        CompanyTrainTaskCustomer trainTaskCustomer = new CompanyTrainTaskCustomer();
        trainTaskCustomer.setCustomerId(companyTrainTask.getSecCustomerId());
        trainTaskCustomer.setTaskId(companyTrainTask.getTaskId());
        if(3 == status){//开启任务
            trainTaskCustomer.setIsParticipation(1);
            trainTaskCustomer.setIsActivation(1);
        }else if(2 == status){//关闭任务
            trainTaskCustomer.setIsActivation(0);
        }
        int flag = companyTrainTaskService.updateTaskCustomer(trainTaskCustomer);
        if(0 >= flag){
            jsonObj.setResult(false);
            jsonObj.setData("操作失败！");
            return jsonObj;
        }
        return jsonObj;
    }


    /**
     * 小蜜后台显示详情
     * @param taskId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showTaskDetail")
    @ResponseBody
    public Object showTaskDetail(Long taskId, Model model, HttpServletRequest request) throws Exception{
        Map<String,Object> map=new HashMap<String,Object>();
        CompanyTrainTask companyTrainTask = companyTrainTaskService.selectTaskById(taskId);
        CompanyTrainClassTask companyTrainClassTask = companyTrainTaskService.selectClassTaskByTaskId(taskId);
        CompanyTrainClass companyTrainClass = companyTrainCLassService.selectByClassId(companyTrainClassTask.getClassId());
        CompanyTrainBase trainBase = companyTrainBaseService.selectBaseByClassId(companyTrainClassTask.getClassId());
        List<CompanyTrainExercises> trainExercises = new ArrayList<CompanyTrainExercises>();
        if(trainBase.getType().equals(EnumUtil.ExercisesBaseType.RANDOM.value)){
            Map<String,Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("customerId",trainBase.getCustomerId());
            paramsMap.put("classTypeId",trainBase.getClassTypeId());
            List<CompanyTrainExercises> trainExercisesList = companyTrainExercisesService.selectRandomExercises(paramsMap);
            trainExercises = returnRandList(trainExercisesList,trainBase.getCount());
        }else{
            trainExercises = companyTrainExercisesService.selectByBaseId(trainBase.getBaseId());
        }
        map.put("exercisesList", trainExercises);
        map.put("trainBase", trainBase);
        map.put("companyTrainClass", companyTrainClass);
        map.put("companyTrainTask", companyTrainTask);
        return map;
    }

    /**
     * 点击查看详情
     * @param taskComment
     * @param model
     * @param request
     * @returni
     * @throws Exception
     */
    @RequestMapping(value = "/appShowTaskDetail")
    @ResponseBody
    public Object appShowTaskDetail(CompanyTrainTaskComment taskComment,String customerId,String userId, Model model, HttpServletRequest request) throws Exception{
        JsonVo<Map<String,Object>> jsonObj = new JsonVo<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",userId);
        params.put("secCustomerId",customerId);
        params.put("taskId",taskComment.getTaskId());
        CompanyTrainTask companyTrainTask = companyTrainTaskService.selectTaskDetailById(params);
        CompanyTrainClassTask companyTrainClassTask = companyTrainTaskService.selectClassTaskByTaskId(taskComment.getTaskId());
        CompanyTrainClass companyTrainClass = companyTrainCLassService.selectByClassId(companyTrainClassTask.getClassId());
        companyTrainCLassService.addReadNum(companyTrainClass);
        List<CompanyTrainTaskComment> taskCommentList = companyTrainTaskService.selectCommentByTaskIdListPage(taskComment);
        CompanyTrainTaskLike trainTaskLike = new CompanyTrainTaskLike();
        trainTaskLike.setCustomerId(customerId);
        trainTaskLike.setTaskId(taskComment.getTaskId());
        trainTaskLike.setUserId(userId);
        int flag = companyTrainTaskService.selectTaskLikeById(trainTaskLike);
        map.put("taskCommentList",taskCommentList);
        map.put("companyTrainClass", companyTrainClass);
        map.put("companyTrainTask", companyTrainTask);
        map.put("isLike", flag > 0);
        jsonObj.setData(map);
        return jsonObj;
    }

    /**
     * 显示评论
     * @param taskComment（currentPage：当前页，showCount：每页多少条）
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showComment")
    @ResponseBody
    public Object showComment(CompanyTrainTaskComment taskComment, Model model, HttpServletRequest request) throws Exception{
        JsonVo<Map<String,Object>> jsonObj = new JsonVo<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        List<CompanyTrainTaskComment> taskCommentList = companyTrainTaskService.selectCommentByTaskIdListPage(taskComment);
        map.put("taskCommentList",taskCommentList);
        jsonObj.setData(map);
        return jsonObj;
    }

    /**
     * 抢赏金
     * @param taskId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/robReWardMoney")
    @ResponseBody
    public Object robReWardMoney(Long taskId,String customerId,String userId,String type, Model model, HttpServletRequest request) throws Exception{
        if(null == taskId || "".equals(taskId) || "".equals(customerId) || null == customerId
                || "".equals(userId) || null == userId){
            throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
        }
        JsonVo<Map<String,Object>> jsonObj = new JsonVo<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();

        map = companyTrainTaskService.rokeMoney(taskId,customerId,userId);
        if(null == type){//type不传的时候传题目，IOS不需要传type
            CompanyTrainClassTask companyTrainClassTask = companyTrainTaskService.selectClassTaskByTaskId(taskId);
            map.putAll(getExercises(companyTrainClassTask.getClassId()));
        }
        jsonObj.setData(map);
        return jsonObj;
    }

    public List<CompanyTrainExercises> returnRandList(List<CompanyTrainExercises> rands,int number){
        List<CompanyTrainExercises> randsCopy = new ArrayList<CompanyTrainExercises>(rands);
        List<CompanyTrainExercises> randLists = new ArrayList<CompanyTrainExercises>();
        int max = randsCopy.size();
        for (int i = 0;i < number && max > 0;i++,max--){
            randLists.add(rands.remove(random.nextInt(rands.size())));
        }
        return randLists;
    }

    /**
     * 根据classId查询出习题
     * @param classId
     * @return
     * @throws Exception
     */
    public Map<String,Object> getExercises(Long classId) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        CompanyTrainBase trainBase = companyTrainBaseService.selectBaseByClassId(classId);
        List<CompanyTrainExercises> trainExercises = new ArrayList<CompanyTrainExercises>();
        if(trainBase.getType().equals(EnumUtil.ExercisesBaseType.RANDOM.value)){
            Map<String,Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("customerId",trainBase.getCustomerId());
            paramsMap.put("classTypeId",trainBase.getClassTypeId());
            List<CompanyTrainExercises> trainExercisesList = companyTrainExercisesService.selectRandomExercises(paramsMap);
            trainExercises = returnRandList(trainExercisesList,trainBase.getCount());
        }else{
            trainExercises = companyTrainExercisesService.selectByBaseId(trainBase.getBaseId());
        }
        if(null != trainBase){
            Integer read = trainBase.getReadCount() == null ? 0 : trainBase.getReadCount();
            trainBase.setReadCount(read+1);
            companyTrainBaseService.updateBase(trainBase);
        }
        map.put("exercisesList", trainExercises);
        return map;
    }
    /**
     * 查询题目
     * @param taskId
     * @param customerId
     * @param userId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showExercises")
    @ResponseBody
    public Object showExercises(Long taskId,String customerId,String userId, Model model, HttpServletRequest request) throws Exception {
        if(null == taskId || "".equals(taskId) || "".equals(customerId) || null == customerId
                || "".equals(userId) || null == userId){
            throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
        }
        JsonVo<Map<String,Object>> jsonObj = new JsonVo<Map<String,Object>>();
        CompanyTrainTask companyTrainTask = companyTrainTaskService.selectTaskById(taskId);
        CompanyTrainClassTask companyTrainClassTask = companyTrainTaskService.selectClassTaskByTaskId(taskId);
        Map<String,Object> map = new HashMap<String,Object>();
        map = getExercises(companyTrainClassTask.getClassId());
        map.put("companyTrainTask", companyTrainTask);
        jsonObj.setData(map);
        return jsonObj;
    }

    /**
     * APP厂商培训点赞
     * @param taskId
     * @param customerId
     * @param userId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/clickLike")
    @ResponseBody
    public Object clickLike(Long taskId,String customerId,String userId, Model model, HttpServletRequest request) throws Exception{
        if(null == taskId || "".equals(taskId) || null == customerId || "".equals(customerId)
                || null == userId || "".equals(userId)){
            throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
        }
        JsonVo<String> jsonObj = new JsonVo<String>();
        CompanyTrainTaskLike trainTaskLike = new CompanyTrainTaskLike();
        trainTaskLike.setCustomerId(customerId);
        trainTaskLike.setTaskId(taskId);
        trainTaskLike.setUserId(userId);
        int likeCount = companyTrainTaskService.selectTaskLikeById(trainTaskLike);
        if(likeCount > 0){//已经点赞过不能再继续点赞
            throw new ServiceException(ErrorCodes.TASK_IS_LIKE);
        }

        String data = companyTrainTaskService.clickLike(trainTaskLike);
        jsonObj.setData(data);
        return jsonObj;
    }


    /**
     * 新增评论
     * @param taskComment(taskId:任务ID,content:内容,fromUserId:评论人ID,fromUserName:评论人,toUserId:被回复人ID,toUserName:被回复人)
     * @param customerId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addComment")
    @ResponseBody
    public Object addComment(CompanyTrainTaskComment taskComment,String customerId, Model model, HttpServletRequest request) throws Exception{
        JsonVo<String> jsonObj = new JsonVo<String>();
        if(null == taskComment.getTaskId() || "".equals(taskComment.getContent())
                || "".equals(taskComment.getFromUserId()) || "".equals(taskComment.getFromUserName())
                || "".equals(customerId) || null == customerId){
            throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
        }
        int count = companyTrainTaskService.selectCommentByTaskIdAndUserId(taskComment);
        String data = "";
        if(count <= 0){
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("customerId",customerId);
            params.put("userId",taskComment.getFromUserId());
            String result = HttpUtil.getServiceName(ADD_COMMENT_POTIONS,params);
            JSONObject resultObj = JSONObject.fromObject(result);
            boolean res = false;
            try {
                res = (Boolean) resultObj.get("result");
            } catch (Exception e) {
                jsonObj.setResult(false);
                jsonObj.setData("新增积分失败！");
                return jsonObj;
            }
            if(!res){
                jsonObj.setResult(false);
                jsonObj.setData("新增积分失败！");
                return jsonObj;
            }
            data = (String) resultObj.get("data");

        }
//        taskComment.setContent(taskComment.getContent().replaceAll("\\\\","\\\\\\\\"));
        int flag = companyTrainTaskService.insertComment(taskComment);
        if(flag <= 0){
            jsonObj.setResult(false);
            jsonObj.setData("操作失败！");
        }
        jsonObj.setData(data);
        return jsonObj;
    }

    /**
     * 打开红包
     * @param taskUser(userId:用户ID,userName:用户名称,customerId:所属厂商公司,taskId:任务ID,phone:联系电话,
            correct:正确率,customerName:连锁企业名,chainCustomerId:连锁企业ID)
     * @param trainErrorOptions()
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/openMoney")
    @ResponseBody
    public Object openMoney(CompanyTrainTaskUser taskUser, String trainErrorOptions, Model model, HttpServletRequest request) throws Exception{
        if(null == taskUser.getTaskId() || null == taskUser.getUserId() || null == taskUser.getUserName()
                || null == taskUser.getCustomerId() || null == taskUser.getCorrect()
                || null == taskUser.getCustomerName() || null == taskUser.getChainCustomerId() || ("").equals(trainErrorOptions)){
            throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
        }
        JsonVo<String> jsonObj = new JsonVo<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("customerId",taskUser.getChainCustomerId());
        params.put("userId",taskUser.getUserId());
        params.put("taskId",taskUser.getTaskId());
        params.put("status",1);
        List<CompanyTrainTaskLockMoney> taskLockMoneys = companyTrainTaskService.selectLockMoneyById(params);
        if(null != taskLockMoneys && taskLockMoneys.size() > 0){
            for (CompanyTrainTaskLockMoney taskLockMoney : taskLockMoneys) {
                Long lockTime = Long.parseLong(LimitUtil.TASKLOCKTIME) * 60 * 60 * 1000;
                Long time = DateUtil.getIntervalTimes(taskLockMoney.getCreateTime(),new Date());
                if(time >= lockTime){//打开红包，如果超出设定时间，则返回超时 错误码：1019
                    companyTrainTaskService.returnLockMoney(taskLockMoney);
                    throw new ServiceException(ErrorCodes.TASK_LOCK_TIME_OUT);
                }
            }
        }else{
            throw new ServiceException(ErrorCodes.TASK_LOCK_TIME_OUT);
        }



        CompanyTrainTask trainTask = companyTrainTaskService.selectTaskById(taskUser.getTaskId());
        int flag = companyTrainTaskService.openMoney(taskUser,trainTask,taskLockMoneys.get(0),trainErrorOptions);
        if(flag != 0){
            jsonObj.setResult(false);
            jsonObj.setData("打开红包失败！");
        }
        return jsonObj;
    }

    /**
     * 提交练习
     * @param taskUser(userId:用户ID,userName:用户名称,customerId:所属厂商公司,taskId:任务ID,phone:联系电话,
            correct:正确率,customerName:连锁企业名,chainCustomerId:连锁企业ID)
     * @param trainErrorOptions()
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/commitExercise")
    @ResponseBody
    public Object commitExercise(CompanyTrainTaskUser taskUser, String trainErrorOptions, Model model, HttpServletRequest request) throws Exception{
        if(null == taskUser.getTaskId() || null == taskUser.getUserId() || null == taskUser.getUserName()
                || null == taskUser.getCustomerId() || null == taskUser.getCorrect()
                || null == taskUser.getCustomerName() || null == taskUser.getChainCustomerId() || ("").equals(trainErrorOptions)){
            throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
        }
        JsonVo<String> jsonObj = new JsonVo<String>();
        int flag = companyTrainTaskService.commitExercise(taskUser,trainErrorOptions);
        if(flag != 0){
            jsonObj.setResult(false);
            jsonObj.setData("提交练习失败！");
        }
        return jsonObj;
    }

    /**
     * 显示错误题目
     * @param trainErrorOption（customer_id:所属公司  user_id:用户ID task_id:所属任务）
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showErrorOption")
    @ResponseBody
    public Object showErrorOption(CompanyTrainErrorOption trainErrorOption, Model model, HttpServletRequest request) throws Exception{
        JsonVo<Map<String,Object>> jsonObj = new JsonVo<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        List<CompanyTrainExercises> trainExercises = companyTrainExercisesService.selectHistoryExercises(trainErrorOption);
        map.put("trainExercises",trainExercises);
        jsonObj.setData(map);
        return jsonObj;
    }
    
    /**
     * 获取连锁企业培训基金列表
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/listCompanyTrain")
    @ResponseBody
    public Object listCompanyTrain(CompanyAccountWhere accountWhere, Model model, HttpServletRequest request) throws Exception{
    	if(StringUtil.isBlanks(false, accountWhere.getChainCustomerId())){
    		throw new ServiceException(ErrorCodes.CHCID_IS_NULL);
    	}
        JsonVo<List<CompanyAccountWhere>> result = new JsonVo<List<CompanyAccountWhere>>();
        List<CompanyAccountWhere> reList = accoutWhereService.queryAccountWhere2ListPage(accountWhere);
        result.setAllcount(accountWhere.getTotalResult());
        result.setData(reList);
        return result;
    }

    /**
     * 重新学习
     * @param taskId
     * @param customerId
     * @param userId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/againLearn")
    @ResponseBody
    public Object againLearn(Long taskId, String customerId, String userId, Model model, HttpServletRequest request) throws Exception{
        if(StringUtil.isBlanks(false,userId,customerId) || taskId == null){
            throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
        }
        JsonVo<String> result = new JsonVo<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("customerId",customerId);
        params.put("userId",userId);
        params.put("taskId",taskId);
        params.put("status",1);
        List<CompanyTrainTaskLockMoney> lockMoney = companyTrainTaskService.selectLockMoneyById(params);
        CompanyTrainTask companyTrainTask = companyTrainTaskService.selectTaskById(taskId);
        if(null != lockMoney && lockMoney.size() > 0){//当有锁定余额的时候，则删除锁定金额
            for (CompanyTrainTaskLockMoney taskLockMoney : lockMoney) {
                //修改任务的剩余余额
                companyTrainTaskService.returnLockMoney(taskLockMoney);
            }
        }
        return result;
    }

    /**
     * 所有厂商
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryOrgAll")
    @ResponseBody
    public Object queryOrgAll(Model model, HttpServletRequest request) throws Exception{
        JsonVo<List<CompanyOrganization>> result = new JsonVo<List<CompanyOrganization>>();
        List<CompanyOrganization> organizations = companyOrgService.selectOrganization();
        result.setData(organizations);
        return result;
    }

    /**
     * 获取是否有红包未领
     * @param customerId
     * @param userId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getRedStatus")
    @ResponseBody
    public Object getRedByCidAndUserId(String customerId,String userId,Model model, HttpServletRequest request) throws Exception{
        JsonVo<Boolean> result = new JsonVo<Boolean>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("customerId",customerId);
        map.put("userId",userId);
        int count = companyTrainTaskService.getRedByCidAndUserId(map);
        boolean flag = false;
        if(count > 0){
            flag = true;
        }
        result.setData(flag);
        return result;
    }


    @RequestMapping(value = "/sendMessageTest")
    @ResponseBody
    public Object sendMessageTest(@RequestParam("customerIds") List<String> customerIds, HttpServletRequest request) throws Exception{
        JsonVo<Boolean> result = new JsonVo<Boolean>();
        System.out.println(customerIds);
        companyTrainTaskService.sendMessageTest(customerIds);
        return result;
    }


}
