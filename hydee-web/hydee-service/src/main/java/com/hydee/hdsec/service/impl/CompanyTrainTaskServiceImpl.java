package com.hydee.hdsec.service.impl;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.*;
import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.service.CompanyAccountService;
import com.hydee.hdsec.service.CompanyOrgService;
import com.hydee.hdsec.service.CompanyTrainExercisesService;
import com.hydee.hdsec.service.CompanyTrainTaskService;
import com.hydee.hdsec.util.EnumUtil;
import com.hydee.hdsec.util.HttpUtil;
import com.hydee.hdsec.util.Logger;
import com.hydee.hdsec.util.ServerChargeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by King.Liu
 * 2016/8/30.
 */
@Service
public class CompanyTrainTaskServiceImpl implements CompanyTrainTaskService {

    private static final String ISSUE_TASK = "messageCompany/issueTask";
    private static final String ADD_LIKE_POTIONS = "messageCompany/addLike";
    @Resource
    CompanyAccountWhereDao companyAccountWhereDao;

    @Resource
    CompanyOrganizationDao companyOrganizationDao;

    @Autowired
    CompanyTrainTaskDao companyTrainTaskDao;

    @Resource
    CompanyAccountDao companyAccountDao;

    @Autowired
    CompanyAccountService companyAccountService;

    @Autowired
    CompanyUserAccountDao companyUserAccountDao;

    @Autowired
    CompanyTrainExercisesDao companyTrainExercisesDao;

    @Autowired
    CompanyCustomerAccountDao companyCustomerAccountDao;

    @Override
    public List<CompanyTrainTask> queryTaskListPage(CompanyTrainTask companyTrainTask) throws Exception {
        return companyTrainTaskDao.queryTaskListPage(companyTrainTask);
    }

    @Override
    public List<CompanyTrainTask> queryTaskInterfaceListPage(CompanyTrainTask companyTrainTask) throws Exception {
        return companyTrainTaskDao.queryTaskInterfaceListPage(companyTrainTask);
    }

    @Override
    public List<CompanyTrainTask> queryTaskAppListPage(CompanyTrainTask companyTrainTask) throws Exception {
        return companyTrainTaskDao.queryTaskAppListPage(companyTrainTask);
    }

    @Override
    public CompanyTrainTask selectTaskById(Long taskId) throws Exception {
        return companyTrainTaskDao.selectTaskById(taskId);
    }

    @Override
    public CompanyTrainClassTask selectClassTaskByTaskId(Long taskId) throws Exception {
        return companyTrainTaskDao.selectClassTaskByTaskId(taskId);
    }


    /**
     * 修改或新增任务
     *
     * @param companyTrainTask
     * @param classId
     * @param thumbnails
     * @param customer
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int saveOrUpdateTask(CompanyTrainTask companyTrainTask, Long classId, String thumbnails, String customer, CompanyUser user) throws Exception {
        int flag = 0;
        JSONArray jsonArray = JSONArray.fromObject(thumbnails);
        JSONArray customers = JSONArray.fromObject(customer);
        List<CompanyTrainTaskThumbnail> trainTaskThumbnailList = new ArrayList<CompanyTrainTaskThumbnail>();
        List<CompanyTrainTaskCustomer> trainTaskCustomerList = new ArrayList<CompanyTrainTaskCustomer>();
        Long taskId = 0L;
        if (null == companyTrainTask.getTaskId()) {
            companyTrainTask.setStatus(1);//新增，任务状态为草稿
            companyTrainTask.setResidueMoney(companyTrainTask.getMoneyReward());
            companyTrainTaskDao.insertTask(companyTrainTask);
            taskId = companyTrainTask.getTaskId();
            CompanyTrainClassTask trainClassTask = new CompanyTrainClassTask();
            trainClassTask.setClassId(classId);
            trainClassTask.setTaskId(taskId);
            companyTrainTaskDao.insertClassTask(trainClassTask);

        } else {
            taskId = companyTrainTask.getTaskId();
            //返还任务发布的钱
            CompanyAccount companyAccount = companyAccountDao.selectByCustomerId(user.getCustomerId());
            companyAccount.setsType(1);//增加
            companyAccount.setBalance(companyTrainTask.getMoneyReward());
            companyAccountService.updateAccountMoney(companyAccount);

            companyTrainTaskDao.deleteThumbnailByTaskId(taskId);
            companyTrainTaskDao.deleteCustomerByTaskId(taskId);
            companyTrainTaskDao.updateTaskById(companyTrainTask);
            CompanyTrainClassTask trainClassTask = new CompanyTrainClassTask();
            trainClassTask.setClassId(classId);
            trainClassTask.setTaskId(taskId);
            companyTrainTaskDao.updateClassTask(trainClassTask);

        }
        for (int i = 0; i < jsonArray.size(); i++) {
            CompanyTrainTaskThumbnail trainTaskThumbnail = new CompanyTrainTaskThumbnail();
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            if ("".equals((String) jsonObject.get("thumbnail"))) {
                continue;
            }
            trainTaskThumbnail.setImgUrl((String) jsonObject.get("thumbnail"));
            trainTaskThumbnail.setTaskId(taskId);
            trainTaskThumbnailList.add(trainTaskThumbnail);
        }
        if(trainTaskThumbnailList.size() > 0){
            companyTrainTaskDao.insertThumbnail(trainTaskThumbnailList);
        }


        for (int j = 0; j < customers.size(); j++) {
            CompanyTrainTaskCustomer trainTaskCustomer = new CompanyTrainTaskCustomer();
            JSONObject jsonObject = (JSONObject) customers.get(j);
            trainTaskCustomer.setCustomerId((String) jsonObject.get("customerId"));
            trainTaskCustomer.setCustomerName((String) jsonObject.get("customerName"));
            trainTaskCustomer.setTaskId(taskId);
            trainTaskCustomerList.add(trainTaskCustomer);
        }
        companyTrainTaskDao.insertTaskCustomer(trainTaskCustomerList);
        //扣除任务发布的钱
        CompanyAccount companyAccount = companyAccountDao.selectByCustomerId(user.getCustomerId());
        companyAccount.setsType(2);//减少
        companyAccount.setBalance(companyTrainTask.getMoneyReward());
        companyAccountService.updateAccountMoney(companyAccount);

        return flag;
    }

    @Override
    public int updateTaskById(CompanyTrainTask companyTrainTask) throws Exception {
        return companyTrainTaskDao.updateTaskById(companyTrainTask);
    }

    @Override
    public int updateTaskCustomer(CompanyTrainTaskCustomer companyTrainTaskCustomer) throws Exception {
        return companyTrainTaskDao.updateTaskCustomer(companyTrainTaskCustomer);
    }

    @Override
    public List<CompanyTrainTaskThumbnail> selectThumbnailByTaskId(Long taskId) throws Exception {
        return companyTrainTaskDao.selectThumbnailByTaskId(taskId);
    }

    @Override
    public List<CompanyTrainTaskCustomer> selectCustomerByTaskId(Long taskId) throws Exception {
        return companyTrainTaskDao.selectCustomerByTaskId(taskId);
    }

    @Override
    public List<CompanyTrainTaskComment> selectCommentByTaskIdListPage(CompanyTrainTaskComment taskComment) throws Exception {
        return companyTrainTaskDao.selectCommentByTaskIdListPage(taskComment);
    }

    @Override
    public int deleteTask(Long taskId, CompanyUser user) throws Exception {

        companyTrainTaskDao.deleteClassAndTaskByTaskId(taskId);
        companyTrainTaskDao.deleteCustomerByTaskId(taskId);
        companyTrainTaskDao.deleteThumbnailByTaskId(taskId);

        CompanyTrainTask trainTask = companyTrainTaskDao.selectTaskById(taskId);
        CompanyAccount companyAccount = companyAccountDao.selectByCustomerId(user.getCustomerId());
        companyAccount.setsType(1);//增加
        companyAccount.setBalance(trainTask.getResidueMoney());
        companyAccountService.updateAccountMoney(companyAccount);

        companyTrainTaskDao.deleteTaskById(taskId);

        return 0;
    }

    @Override
    public List<CompanyTrainTask> queryTaskByStatus(int status) throws Exception {
        return companyTrainTaskDao.queryTaskByStatus(status);
    }

    @Override
    public int addMoney(CompanyTrainTask trainTask, double addMoney, CompanyUser _user) throws Exception {
        CompanyAccount companyAccount = companyAccountDao.selectByCustomerId(_user.getCustomerId());
        companyAccount.setsType(2);//减少
        companyAccount.setBalance(addMoney);
        companyAccountService.updateAccountMoney(companyAccount);
        companyTrainTaskDao.updateTaskById(trainTask);
        CompanyAccountWhere companyAccountWhere = new CompanyAccountWhere();
        companyAccountWhere.setTaskId(trainTask.getTaskId());
        companyAccountWhere.setDictId(EnumUtil.Dict.ADD_MONEY.value);
        companyAccountWhere.setUserId(_user.getId().toString());
        companyAccountWhere.setUserName(_user.getUserName());
        companyAccountWhere.setPhone(_user.getUserPhone());
        companyAccountWhere.setCustomerId(_user.getCustomerId());
        companyAccountWhere.setCustomerName(_user.getCompany());
        companyAccountWhere.setType(2);
        companyAccountWhere.setSerialNumber(companyAccountWhereDao.getSerialNumber());
        companyAccountWhere.setFundDivided(addMoney);
        companyAccountWhere.setCreateTime(new Date());
        companyAccountWhereDao.insertSelective(companyAccountWhere);

        return 0;
    }

    @Override
    public int insertLockMoney(CompanyTrainTaskLockMoney taskLockMoney) throws Exception {
        return companyTrainTaskDao.insertLockMoney(taskLockMoney);
    }

    @Override
    public int insertComment(CompanyTrainTaskComment taskComment) throws Exception {
        return companyTrainTaskDao.insertComment(taskComment);
    }

    @Override
    public int selectCommentByTaskIdAndUserId(CompanyTrainTaskComment taskComment) throws Exception {
        return companyTrainTaskDao.selectCommentByTaskIdAndUserId(taskComment);
    }

    /**
     * 提交练习
     * @param taskUser
     * @return
     * @throws Exception
     */
    @Override
    public int commitExercise(CompanyTrainTaskUser taskUser, String trainErrorOptions) throws Exception {
        double correct = taskUser.getCorrect();
        taskUser.setCorrect(correct);
        taskUser.setType(1);
        companyTrainTaskDao.insertTaskUser(taskUser);
        List<CompanyTrainErrorOption> trainErrorOptionList = new ArrayList<CompanyTrainErrorOption>();
        if (!"".equals(trainErrorOptions)) {
            JSONArray jsonArray = JSONArray.fromObject(trainErrorOptions);
            for (int i = 0; i < jsonArray.size(); i++) {
                CompanyTrainErrorOption errorOption = new CompanyTrainErrorOption();
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                errorOption.setExercisesId(Long.parseLong(jsonObject.get("exercisesId").toString()));
                errorOption.setChooseNo((String) jsonObject.get("chooseNo"));
                errorOption.setCustomerId((String) jsonObject.get("customerId"));
                errorOption.setUserId((String) jsonObject.get("userId"));
                errorOption.setTaskId(Long.parseLong(jsonObject.get("taskId").toString()));
                trainErrorOptionList.add(errorOption);
            }
            if (trainErrorOptionList.size() > 0) {
                //新增错误题目
                companyTrainExercisesDao.insertErrorOption(trainErrorOptionList);
            }
        }

        return 0;
    }

    /**
     * 打开红包
     *
     * @param taskUser
     * @return
     * @throws Exception
     */
    @Override
    public int openMoney(CompanyTrainTaskUser taskUser, CompanyTrainTask trainTask, CompanyTrainTaskLockMoney taskLockMoneys, String trainErrorOptions) throws Exception {
        int commissionType = trainTask.getCommissionType();
        double correct = taskUser.getCorrect();
        double redMoney = 0;
        if (correct > 0 && correct < 60) {
            redMoney = trainTask.getAwardLow();
        } else if (correct >= 60 && correct < 100) {
            redMoney = trainTask.getAwardMiddle();
        } else if (correct == 100) {
            redMoney = trainTask.getAwardHigh();
        }


        List<Long> dict = new ArrayList<Long>();
        dict.add(EnumUtil.Dict.TRAIN_FUND.value);
        dict.add(EnumUtil.Dict.SERVE_CHARGE.value);
        dict.add(EnumUtil.Dict.RED_MONEY.value);
        double serveCharge = 0, trainFund = 0;
        //新增资金去向
        if (commissionType == 1) {//按钱来新增培训基金和服务费
            serveCharge = trainTask.getServeCharge() == null ? Double.parseDouble(ServerChargeUtil.SERVERCHARGE) : trainTask.getServeCharge();
            trainFund = trainTask.getTrainFund();
        } else if (commissionType == 2) {//按百分比来算培训基金和服务费
            double servePercent = trainTask.getServeCharge() == null ? Double.parseDouble(ServerChargeUtil.SERVERCHARGE) : trainTask.getServeCharge();
            double fundPercent = trainTask.getTrainFund();
            serveCharge = redMoney * (servePercent / 100);
            trainFund = redMoney * (fundPercent / 100);
        }

        double trainMoney = serveCharge + trainFund;//平台抽取余额
        double lockMoney = taskLockMoneys.getLockMoney();

        double customerBalance = trainFund;
        if (lockMoney <= trainMoney) {//如果平台抽取金额大于等于锁定金额，则不抽取平台费
            redMoney = lockMoney;
            customerBalance = 0;
        }

        taskUser.setRedMoney(redMoney);
        taskUser.setCorrect(correct);
        companyTrainTaskDao.insertTaskUser(taskUser);

        List<CompanyTrainErrorOption> trainErrorOptionList = new ArrayList<CompanyTrainErrorOption>();
        if (!"".equals(trainErrorOptions)) {
            JSONArray jsonArray = JSONArray.fromObject(trainErrorOptions);
            for (int i = 0; i < jsonArray.size(); i++) {
                CompanyTrainErrorOption errorOption = new CompanyTrainErrorOption();
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                errorOption.setExercisesId(Long.parseLong(jsonObject.get("exercisesId").toString()));
                errorOption.setChooseNo((String) jsonObject.get("chooseNo"));
                errorOption.setCustomerId((String) jsonObject.get("customerId"));
                errorOption.setUserId((String) jsonObject.get("userId"));
                errorOption.setTaskId(Long.parseLong(jsonObject.get("taskId").toString()));
                trainErrorOptionList.add(errorOption);
            }
            if (trainErrorOptionList.size() > 0) {
                //新增错误题目
                companyTrainExercisesDao.insertErrorOption(trainErrorOptionList);
            }
        }

        CompanyAccountWhere accountWhere = new CompanyAccountWhere();
        accountWhere.setTaskId(taskUser.getTaskId());
        accountWhere.setUserId(taskUser.getUserId());
        accountWhere.setUserName(taskUser.getUserName());
        accountWhere.setPhone(taskUser.getPhone());
        accountWhere.setCustomerId(taskUser.getCustomerId());
        accountWhere.setCustomerName(taskUser.getCustomerName());
        accountWhere.setType(2);
        accountWhere.setSerialNumber(companyAccountWhereDao.getSerialNumber());
        accountWhere.setRedMoney(redMoney);
        accountWhere.setCorrect(correct);
        accountWhere.setServeCharge(redMoney == lockMoney ? 0 : serveCharge);
        accountWhere.setTrainFund(redMoney == lockMoney ? 0 : trainFund);
        accountWhere.setChainCustomerId(taskUser.getChainCustomerId());
        for (Long dictId : dict) {
            accountWhere.setDictId(dictId);
            companyAccountWhereDao.insertSelective(accountWhere);
        }
        CompanyUserAccount userAccount = new CompanyUserAccount();
        userAccount.setBalance(redMoney);
        userAccount.setUserId(taskUser.getUserId());
        userAccount.setCustomerId(taskUser.getChainCustomerId());
        CompanyUserAccount companyUserAccount = companyUserAccountDao.selectUserAccountByUidAndCid(userAccount);
        if (null != companyUserAccount) {
            double balance = companyUserAccount.getBalance();
            companyUserAccount.setBalance(redMoney + balance);
            companyUserAccountDao.updateUserAccount(companyUserAccount);
        } else {
            companyUserAccountDao.insertUserAccount(userAccount);
        }
        CompanyCustomerAccount companyCustomerAccount = companyCustomerAccountDao.selectCustomerAccount(taskUser.getChainCustomerId());
        CompanyCustomerAccount customerAccount = new CompanyCustomerAccount();
        customerAccount.setBalance(customerBalance);//企业获得培训基金
        customerAccount.setCustomerId(taskUser.getChainCustomerId());
        customerAccount.setCustomerName(taskUser.getCustomerName());
        if (null != companyCustomerAccount) {
            double balance = companyCustomerAccount.getBalance();
            customerAccount.setBalance(balance + customerBalance);
            companyCustomerAccountDao.updateCustomerAccount(customerAccount);
        } else {
            companyCustomerAccountDao.insertCustomerAccount(customerAccount);
        }

        taskLockMoneys.setStatus(0);//状态改成删除
        companyTrainTaskDao.updateLockMoney(taskLockMoneys);
        return 0;
    }

    /**
     * 发布任务
     *
     * @param companyTrainTask
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public int issueTask(CompanyTrainTask companyTrainTask, CompanyUser user) throws Exception {
        companyTrainTaskDao.updateTaskById(companyTrainTask);
        List<CompanyTrainTaskCustomer> taskCustomerList = companyTrainTaskDao.selectCustomerByTaskId(companyTrainTask.getTaskId());
        CompanyTrainTask trainTask = companyTrainTaskDao.selectTaskById(companyTrainTask.getTaskId());
        JSONArray customerIds = new JSONArray();
        for (CompanyTrainTaskCustomer taskCustomer : taskCustomerList) {
            String customer = taskCustomer.getCustomerId();
            customerIds.add(customer);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("customerId", customerIds.toString());
        params.put("customerName", user.getCompany());
        params.put("moneyReward", trainTask.getMoneyReward().toString());
        params.put("startTime", trainTask.getStartTimeFmt());
        params.put("endTime", trainTask.getEndTimeFmt());
        params.put("taskId", trainTask.getTaskId());
        String result = HttpUtil.getServiceName(ISSUE_TASK, params);
        JSONObject resultObj = JSONObject.fromObject(result);
        boolean res = (Boolean) resultObj.get("result");
        if (!res) {
            throw new ServiceException(ErrorCodes.ISSUE_TASK_ERROR);
        }

        return 0;
    }

    @Override
    public List<CompanyTrainTask> selectTaskByClassId(Long classId) throws Exception {
        return companyTrainTaskDao.selectTaskByClassId(classId);
    }

    @Override
    public CompanyTrainTask selectTaskByCidAndTaskId(Map<String, Object> map) throws Exception {
        return companyTrainTaskDao.selectTaskByCidAndTaskId(map);
    }

    @Override
    public List<CompanyTrainTaskLockMoney> selectLockMoneyById(Map<String, Object> map) throws Exception {
        return companyTrainTaskDao.selectLockMoneyById(map);
    }


    @Override
    public CompanyTrainTaskUser selectTaskUserByCidAndUserId(CompanyTrainTaskUser taskUser) throws Exception {
        return companyTrainTaskDao.selectTaskUserByCidAndUserId(taskUser);
    }

    @Override
    public int selectTaskLikeById(CompanyTrainTaskLike trainTaskLike) throws Exception {
        return companyTrainTaskDao.selectTaskLikeById(trainTaskLike);
    }

    @Override
    public int insertTaskLike(CompanyTrainTaskLike trainTaskLike) throws Exception {
        return companyTrainTaskDao.insertTaskLike(trainTaskLike);
    }

    @Override
    public int updateLockMoney(CompanyTrainTaskLockMoney taskLockMoney) throws Exception {
        return companyTrainTaskDao.updateLockMoney(taskLockMoney);
    }

    @Override
    public CompanyTrainTask selectTaskDetailById(Map<String, Object> map) throws Exception {
        return companyTrainTaskDao.selectTaskDetailById(map);
    }

    @Override
    public double getTaskFrozenCharge(String customerId) throws Exception {
        return companyTrainTaskDao.getTaskFrozenCharge(customerId);
    }

    @Override
    public int selectTaskUserAccount(String customerId) throws Exception {
        return companyTrainTaskDao.selectTaskUserAccount(customerId);
    }

    /**
     * 抢赏金锁定余额
     *
     * @param taskId
     * @param customerId
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> rokeMoney(Long taskId, String customerId, String userId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String errorType = "";
        CompanyTrainTask companyTrainTask = companyTrainTaskDao.selectTaskById(taskId);
        if (3 != companyTrainTask.getStatus()) {//只有进行中的才能抢赏金 错误码 1016
            throw new ServiceException(ErrorCodes.TASK_NOT_DOING);
        }
        CompanyTrainTaskUser trainTaskUser = new CompanyTrainTaskUser();
        trainTaskUser.setCustomerId(customerId);
        trainTaskUser.setUserId(userId);
        trainTaskUser.setTaskId(taskId);
        CompanyTrainTaskUser taskUser = companyTrainTaskDao.selectTaskUserByCidAndUserId(trainTaskUser);
        if (null != taskUser) {//参加过的人员不能再参加 错误码 1018
            throw new ServiceException(ErrorCodes.TASK_USER_EXIST);
        }

        double trainFund = companyTrainTask.getTrainFund();
        double serveCharge = companyTrainTask.getServeCharge() == null ? Double.parseDouble(ServerChargeUtil.SERVERCHARGE) : companyTrainTask.getServeCharge();
        int commissionType = companyTrainTask.getCommissionType();
        double awardHigh = companyTrainTask.getAwardHigh();
        double awardMiddle = companyTrainTask.getAwardMiddle();
        double awardLow = companyTrainTask.getAwardLow();

        if (commissionType == 2) {//按百分比来算培训基金和服务费
            serveCharge = awardHigh * (serveCharge / 100);
            trainFund = awardHigh * (trainFund / 100);
        }
        double account = trainFund + serveCharge + awardHigh;
        double residueMoney = companyTrainTask.getResidueMoney();

        Map<String, Object> taskParams = new HashMap<String, Object>();
        taskParams.put("taskId", taskId);
        taskParams.put("status", 1);
        List<CompanyTrainTaskLockMoney> lockMoneys = companyTrainTaskDao.selectLockMoneyById(taskParams);
        if (null != lockMoneys && lockMoneys.size() > 0 && residueMoney == 0) {//如果锁定金额里面有余额，但剩余余额为0，提示稍后再来 错误码 1020
            throw new ServiceException(ErrorCodes.TASK_RESIDUE_MONEY_NOT);
        }

        if (null == lockMoneys && lockMoneys.size() <= 0 && residueMoney == 0) {//如果锁定金额里面没有余额，剩余余额为0，任务已结束 错误码 1026
            throw new ServiceException(ErrorCodes.TASK_IS_OVER);
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("customerId", customerId);
        params.put("userId", userId);
        params.put("taskId", taskId);
        List<CompanyTrainTaskLockMoney> lockMoney = companyTrainTaskDao.selectLockMoneyById(params);
        if (null != lockMoney && lockMoney.size() > 0) {//当有锁定余额的时候，则删除锁定金额
            for (CompanyTrainTaskLockMoney taskLockMoney : lockMoney) {
                taskLockMoney.setCreateTime(new Date());
                if (taskLockMoney.getStatus() == 0) {//当前状态为被删除时，则修改成锁定状态,修改一下它的创建时间
                    taskLockMoney.setStatus(1);
                } else if (taskLockMoney.getStatus() == 1) {//状态为正在锁定时，则将锁定的钱返回到剩余余额
                    //修改任务的剩余余额
                    companyTrainTask.setStatus(2);
                    companyTrainTask.setResidueMoney(residueMoney + taskLockMoney.getLockMoney());
                    int lockTask = companyTrainTaskDao.updateTaskById(companyTrainTask);
                    if (lockTask <= 0) {
                        throw new ServiceException(ErrorCodes.TASK_UPDATE);
                    }
                }
                taskLockMoney.setLockMoney(account > companyTrainTask.getResidueMoney() ? companyTrainTask.getResidueMoney() : account);//如果剩余金额小于锁定余额，则取所有剩余余额
                companyTrainTaskDao.updateLockMoney(taskLockMoney);
            }
        } else {//当没有锁定余额时，新增一条锁定余额记录
            CompanyTrainTaskLockMoney taskLockMoney = new CompanyTrainTaskLockMoney();
            taskLockMoney.setLockMoney(account > residueMoney ? residueMoney : account);//如果剩余金额小于锁定余额，则取所有剩余余额
            taskLockMoney.setTaskId(companyTrainTask.getTaskId());
            taskLockMoney.setCustomerId(customerId);
            taskLockMoney.setUserId(userId);

            //新增锁余额
            int flag = companyTrainTaskDao.insertLockMoney(taskLockMoney);
            if (flag <= 0) {
                throw new ServiceException(ErrorCodes.TASK_LOCK_MONEY);
            }
        }


        //修改任务的剩余余额
        companyTrainTask.setStatus(2);
        companyTrainTask.setResidueMoney(companyTrainTask.getResidueMoney() < account ? 0 : companyTrainTask.getResidueMoney() - account);
        int flag2 = companyTrainTaskDao.updateTaskById(companyTrainTask);
        if (flag2 <= 0) {
            throw new ServiceException(ErrorCodes.TASK_UPDATE);
        }

        if (account > residueMoney) {
            errorType = "当前满分红包金额仅剩" + residueMoney + "元，请尽快考试领取红包噢";
            if (awardHigh > residueMoney) {
                companyTrainTask.setAwardHigh(residueMoney);
            }
            if (awardMiddle > residueMoney) {
                companyTrainTask.setAwardMiddle(residueMoney);
            }
            if (awardLow > residueMoney) {
                companyTrainTask.setAwardLow(residueMoney);
            }
        }
        map.put("companyTrainTask", companyTrainTask);
        map.put("errorType", errorType);
        return map;
    }

    @Override
    public String clickLike(CompanyTrainTaskLike trainTaskLike) throws Exception {
        CompanyTrainTask companyTrainTask = companyTrainTaskDao.selectTaskById(trainTaskLike.getTaskId());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("customerId", trainTaskLike.getCustomerId());
        params.put("userId", trainTaskLike.getUserId());
        String result = HttpUtil.getServiceName(ADD_LIKE_POTIONS, params);
        JSONObject resultObj = JSONObject.fromObject(result);
        boolean res = (Boolean) resultObj.get("result");
        if (!res) {
            throw new ServiceException(ErrorCodes.CLICK_LIKE_NUM);
        }
        String data = (String) resultObj.get("data");
        Long like = companyTrainTask.getLikeNum();
        like++;
        companyTrainTask.setLikeNum(like);
        companyTrainTask.setStatus(2);
        companyTrainTaskDao.updateTaskById(companyTrainTask);
        companyTrainTaskDao.insertTaskLike(trainTaskLike);
        return data;
    }

    @Override
    public int returnLockMoney(CompanyTrainTaskLockMoney taskLockMoney) throws Exception {
        taskLockMoney.setStatus(0);
        companyTrainTaskDao.updateLockMoney(taskLockMoney);
        CompanyTrainTask trainTask = companyTrainTaskDao.selectTaskById(taskLockMoney.getTaskId());
        trainTask.setResidueMoney(taskLockMoney.getLockMoney() + trainTask.getResidueMoney());
        trainTask.setStatus(2);
        companyTrainTaskDao.updateTaskById(trainTask);
        return 0;
    }


    @Override
    public List<CompanyTrainTask> queryTask2ListPage(
            CompanyTrainTask companyTrainTask) throws Exception {
        // TODO Auto-generated method stub
        return companyTrainTaskDao.queryTask2ListPage(companyTrainTask);
    }

    @Override
    public int extract(Long taskId, CompanyUser _user) {
        // TODO Auto-generated method stub

        int result = 0;
        CompanyTrainTask task = companyTrainTaskDao.selectTaskById(taskId);
        //没有找到任务
        if (task == null) return 0;
        //可提取余额为0
        if (task.getResidueMoney() == 0) return 0;
        //不是操作本用户的余额
        if (task.getCreateId() != _user.getId()) return 0;

        CompanyOrganization companyOrganization = companyOrganizationDao.selectByPrimaryKey(_user.getCustomerId());
        if (companyOrganization == null) return 0;

        //任务ID，资金类型，用户ID，所属公司，公司名称，类别，流水号 红包
        CompanyAccountWhere companyAccountWhere = new CompanyAccountWhere();
        companyAccountWhere.setTaskId(taskId);
        companyAccountWhere.setDictId(EnumUtil.Dict.USABLE_MONEY.value);
        companyAccountWhere.setUserId(_user.getId().toString());
        companyAccountWhere.setUserName(_user.getUserName());
        companyAccountWhere.setPhone(_user.getUserPhone());
        companyAccountWhere.setCustomerId(companyOrganization.getCustomerId());
        companyAccountWhere.setCustomerName(companyOrganization.getOrgName());
        companyAccountWhere.setType(2);
        companyAccountWhere.setSerialNumber(companyAccountWhereDao.getSerialNumber());
        companyAccountWhere.setRedMoney(task.getResidueMoney());
        companyAccountWhere.setCreateTime(new Date());

        CompanyAccount companyAccount = companyAccountDao.selectByCustomerId(_user.getCustomerId());
        companyAccount.setsType(1);//增加
        companyAccount.setBalance(task.getResidueMoney());
        companyAccountService.updateAccountMoney(companyAccount);
        result = companyAccountWhereDao.insertSelective(companyAccountWhere);

        if (result != 0) {
            CompanyTrainTask task1 = new CompanyTrainTask();
            task1.setTaskId(taskId);
            task1.setResidueMoney((double) 0);
            result = companyTrainTaskDao.updateTaskById(task1);
        }
        return result;
    }
}
