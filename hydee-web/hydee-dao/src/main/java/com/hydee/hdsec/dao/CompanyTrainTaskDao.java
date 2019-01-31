package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.*;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTrainTaskDao {

    List<CompanyTrainTask> queryTaskListPage(CompanyTrainTask companyTrainTask);
    
    List<CompanyTrainTask> queryTask2ListPage(CompanyTrainTask companyTrainTask);

    List<CompanyTrainTask> queryTaskInterfaceListPage(CompanyTrainTask companyTrainTask);

    List<CompanyTrainTask> queryTaskAppListPage(CompanyTrainTask companyTrainTask);

    List<CompanyTrainTask> queryTaskByStatus(int status);

    List<CompanyTrainTask> selectTaskByClassId(Long classId);

    CompanyTrainTask selectTaskDetailById(Map<String,Object> map);

    int deleteTaskById(Long taskId);

    int deleteThumbnailByTaskId(Long taskId);

    int deleteCustomerByTaskId(Long taskId);

    int deleteClassAndTaskByTaskId(Long taskId);

//    int deleteLockMoney(Long id);

    int insertTask(CompanyTrainTask record);

    int insertThumbnail(List<CompanyTrainTaskThumbnail> companyTrainTaskThumbnails);

    int insertClassTask(CompanyTrainClassTask companyTrainClassTask);

    int insertTaskCustomer(List<CompanyTrainTaskCustomer> trainTaskCustomers);

    int insertLockMoney(CompanyTrainTaskLockMoney taskLockMoney);

    int insertComment(CompanyTrainTaskComment taskComment);

    int insertTaskUser(CompanyTrainTaskUser taskUser);

    CompanyTrainTask selectTaskById(Long taskId);

    CompanyTrainTask selectTaskByCidAndTaskId(Map<String,Object> map);

    int updateTaskById(CompanyTrainTask record);

    int updateClassTask(CompanyTrainClassTask companyTrainClassTask);

    int updateTaskCustomer(CompanyTrainTaskCustomer companyTrainTaskCustomer);

    CompanyTrainClassTask selectClassTaskByTaskId(Long taskId);

    List<CompanyTrainTaskThumbnail> selectThumbnailByTaskId(Long taskId);

    List<CompanyTrainTaskCustomer> selectCustomerByTaskId(Long taskId);

    List<CompanyTrainTaskComment> selectCommentByTaskIdListPage(CompanyTrainTaskComment taskComment);

//    List<CompanyTrainTaskLockMoney> selectLockMoney();

    List<CompanyTrainTaskLockMoney> selectLockMoneyById(Map<String,Object> map);

//    List<CompanyTrainTaskLockMoney> selectLockMoneyByTaskId(Long taskId);

    int selectCommentByTaskIdAndUserId(CompanyTrainTaskComment taskComment);

    CompanyTrainTaskUser selectTaskUserByCidAndUserId(CompanyTrainTaskUser taskUser);

    int selectTaskLikeById(CompanyTrainTaskLike trainTaskLike);

    int insertTaskLike(CompanyTrainTaskLike trainTaskLike);

    int updateLockMoney(CompanyTrainTaskLockMoney taskLockMoney);

    double getTaskFrozenCharge(String customerId);

    int selectTaskUserAccount(String customerId);
}