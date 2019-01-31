package com.hydee.hdsec.service;

import com.hydee.hdsec.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by King.Liu
 * 2016/8/30.
 */
public interface CompanyTrainTaskService {

    /**
     * 分页查询
     * @param companyTrainTask
     * @return
     * @throws Exception
     */
    public List<CompanyTrainTask> queryTaskListPage(CompanyTrainTask companyTrainTask) throws Exception;
    
    /**
     * 分页查询
     * @param companyTrainTask
     * @return
     * @throws Exception
     */
    public List<CompanyTrainTask> queryTask2ListPage(CompanyTrainTask companyTrainTask) throws Exception;

    /**
     * 小蜜接口显示厂商列表数据
     * @param companyTrainTask
     * @return
     * @throws Exception
     */
    public List<CompanyTrainTask> queryTaskInterfaceListPage(CompanyTrainTask companyTrainTask) throws Exception;

    /**
     * APP厂商列表数据
     * @param companyTrainTask
     * @return
     * @throws Exception
     */
    public List<CompanyTrainTask> queryTaskAppListPage(CompanyTrainTask companyTrainTask) throws Exception;

    public CompanyTrainTask selectTaskById(Long taskId) throws Exception;

    public CompanyTrainClassTask selectClassTaskByTaskId(Long taskId) throws Exception;

    public int saveOrUpdateTask(CompanyTrainTask companyTrainTask,Long classId,String thumbnails,String customer,CompanyUser user) throws Exception;

    public int updateTaskById(CompanyTrainTask companyTrainTask) throws Exception;

    public int updateTaskCustomer(CompanyTrainTaskCustomer companyTrainTaskCustomer) throws Exception;

    List<CompanyTrainTaskThumbnail> selectThumbnailByTaskId(Long taskId) throws Exception;

    List<CompanyTrainTaskCustomer> selectCustomerByTaskId(Long taskId) throws Exception;

    List<CompanyTrainTaskComment> selectCommentByTaskIdListPage(CompanyTrainTaskComment taskComment) throws Exception;

    /**
     * 提交任务到资金去向
     * */
	public int extract(Long taskId, CompanyUser _user);


    public int deleteTask(Long taskId,CompanyUser user) throws Exception;

//    public int deleteLockMoney(Long id) throws Exception;

    public List<CompanyTrainTask> queryTaskByStatus(int status) throws Exception;

    /**
     * 增加奖金
     * @param trainTask
     * @return
     * @throws Exception
     */
    public int addMoney(CompanyTrainTask trainTask,double addMoney, CompanyUser _user) throws Exception;

    public int insertLockMoney(CompanyTrainTaskLockMoney taskLockMoney) throws Exception;

    public int insertComment(CompanyTrainTaskComment taskComment) throws Exception;

    public int selectCommentByTaskIdAndUserId(CompanyTrainTaskComment taskComment) throws Exception;

    /**
     * 打开红包
     * @param taskUser
     * @param trainTask
     * @param taskLockMoneys
     * @param trainErrorOptions
     * @return
     * @throws Exception
     */
    public int openMoney(CompanyTrainTaskUser taskUser,CompanyTrainTask trainTask,CompanyTrainTaskLockMoney taskLockMoneys,String trainErrorOptions) throws Exception;

    /**
     * 已经结束任务，提交练习
     * @param taskUser
     * @param trainErrorOptions
     * @return
     * @throws Exception
     */
    public int commitExercise(CompanyTrainTaskUser taskUser,String trainErrorOptions) throws Exception;


    /**
     * 发布任务
     * @param trainTask
     * @return
     * @throws Exception
     */
    public int issueTask(CompanyTrainTask trainTask,CompanyUser user) throws Exception;

    public List<CompanyTrainTask> selectTaskByClassId(Long classId) throws Exception;

    public CompanyTrainTask selectTaskByCidAndTaskId(Map<String,Object> map) throws Exception;
    /**
     * 根据customerId和UserId或taskId查询锁定金额
     * @param map
     * @return
     * @throws Exception
     */
    public List<CompanyTrainTaskLockMoney> selectLockMoneyById(Map<String,Object> map) throws Exception;

//    public List<CompanyTrainTaskLockMoney> selectLockMoneyByTaskId(Long taskId) throws Exception;

    public CompanyTrainTaskUser selectTaskUserByCidAndUserId(CompanyTrainTaskUser taskUser) throws Exception;

    public int selectTaskLikeById(CompanyTrainTaskLike trainTaskLike) throws Exception;

    public int insertTaskLike(CompanyTrainTaskLike trainTaskLike) throws Exception;

    public int updateLockMoney(CompanyTrainTaskLockMoney taskLockMoney) throws Exception;

    public CompanyTrainTask selectTaskDetailById(Map<String, Object> map) throws Exception;

    public double getTaskFrozenCharge(String customerId) throws Exception;

    public int selectTaskUserAccount(String customerId) throws Exception;

    public Map<String,Object> rokeMoney(Long taskId,String customerId,String userId) throws Exception;

    public String clickLike(CompanyTrainTaskLike trainTaskLike) throws Exception;

    public int returnLockMoney(CompanyTrainTaskLockMoney taskLockMoney) throws Exception;
}
