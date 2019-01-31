package com.hydee.hdsec.dao.impl;

import com.hydee.hdsec.dao.CompanyTrainTaskDao;
import com.hydee.hdsec.dao.base.BaseDao;
import com.hydee.hdsec.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by King.Liu
 * 2016/8/30.
 */
//@Repository("companyTrainTaskDao")
public class CompanyTrainTaskDaoImpl extends BaseDao implements CompanyTrainTaskDao {
    @Override
    public List<CompanyTrainTask> queryTaskListPage(CompanyTrainTask companyTrainTask) {
        return (List<CompanyTrainTask>) findForList("CompanyTrainTaskMapper.queryTaskListPage",companyTrainTask);
    }

    @Override
    public List<CompanyTrainTask> queryTaskInterfaceListPage(CompanyTrainTask companyTrainTask) {
        return (List<CompanyTrainTask>) findForList("CompanyTrainTaskMapper.queryTaskInterfaceListPage",companyTrainTask);
    }

    @Override
    public List<CompanyTrainTask> queryTaskAppListPage(CompanyTrainTask companyTrainTask) {
        return (List<CompanyTrainTask>) findForList("CompanyTrainTaskMapper.queryTaskAppListPage",companyTrainTask);
    }

    @Override
    public List<CompanyTrainTask> queryTaskByStatus(int status) {
        return (List<CompanyTrainTask>) findForList("CompanyTrainTaskMapper.queryTaskByStatus",status);
    }

    @Override
    public List<CompanyTrainTask> selectTaskByClassId(Long classId) {
        return (List<CompanyTrainTask>) findForList("CompanyTrainTaskMapper.selectTaskByClassId",classId);
    }

    @Override
    public CompanyTrainTask selectTaskDetailById(Map<String, Object> map) {
        return (CompanyTrainTask) findForObject("CompanyTrainTaskMapper.selectTaskDetailById",map);
    }

    @Override
    public int deleteTaskById(Long taskId) {
        return (Integer) delete("CompanyTrainTaskMapper.deleteTaskById",taskId);
    }

    @Override
    public int deleteThumbnailByTaskId(Long taskId) {
        return (Integer) delete("CompanyTrainTaskMapper.deleteThumbnailByTaskId",taskId);
    }

    @Override
    public int deleteCustomerByTaskId(Long taskId) {
        return (Integer) delete("CompanyTrainTaskMapper.deleteCustomerByTaskId",taskId);
    }

    @Override
    public int deleteClassAndTaskByTaskId(Long taskId) {
        return (Integer) delete("CompanyTrainTaskMapper.deleteClassAndTaskByTaskId",taskId);
    }

//    @Override
//    public int deleteLockMoney(Long id) {
//        return (Integer) delete("CompanyTrainTaskMapper.deleteLockMoney",id);
//    }

    @Override
    public int insertTask(CompanyTrainTask record) {
        return (Integer) save("CompanyTrainTaskMapper.insertTask",record);
    }

    @Override
    public int insertThumbnail(List<CompanyTrainTaskThumbnail> companyTrainTaskThumbnails) {
        return (Integer) batchInsert("CompanyTrainTaskMapper.insertThumbnail",companyTrainTaskThumbnails);
    }

    @Override
    public int insertClassTask(CompanyTrainClassTask companyTrainClassTask) {
        return (Integer) save("CompanyTrainTaskMapper.insertClassTask",companyTrainClassTask);
    }

    @Override
    public int insertTaskCustomer(List<CompanyTrainTaskCustomer> trainTaskCustomers) {
        return (Integer) batchInsert("CompanyTrainTaskMapper.insertTaskCustomer",trainTaskCustomers);
    }

    @Override
    public int insertLockMoney(CompanyTrainTaskLockMoney taskLockMoney) {
        return (Integer) save("CompanyTrainTaskMapper.insertLockMoney",taskLockMoney);
    }

    @Override
    public int insertComment(CompanyTrainTaskComment taskComment) {
        return (Integer) save("CompanyTrainTaskMapper.insertComment",taskComment);
    }

    @Override
    public int insertTaskUser(CompanyTrainTaskUser taskUser) {
        return (Integer) save("CompanyTrainTaskMapper.insertTaskUser",taskUser);
    }

    @Override
    public CompanyTrainTask selectTaskById(Long taskId) {
        return (CompanyTrainTask) findForObject("CompanyTrainTaskMapper.selectTaskById",taskId);
    }

    @Override
    public CompanyTrainTask selectTaskByCidAndTaskId(Map<String, Object> map) {
        return null;
    }

    @Override
    public int getRedByCidAndUserId(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int updateTaskById(CompanyTrainTask record) {
        return (Integer) update("CompanyTrainTaskMapper.updateTaskById",record);
    }

    @Override
    public int updateClassTask(CompanyTrainClassTask companyTrainClassTask) {
        return (Integer) update("CompanyTrainTaskMapper.updateClassTask",companyTrainClassTask);
    }

    @Override
    public int updateTaskCustomer(CompanyTrainTaskCustomer companyTrainTaskCustomer) {
        return (Integer) update("CompanyTrainTaskMapper.updateTaskCustomer",companyTrainTaskCustomer);
    }

    @Override
    public CompanyTrainClassTask selectClassTaskByTaskId(Long taskId) {
        return (CompanyTrainClassTask) findForObject("CompanyTrainTaskMapper.selectClassTaskByTaskId",taskId);
    }

    @Override
    public List<CompanyTrainTaskThumbnail> selectThumbnailByTaskId(Long taskId) {
        return (List<CompanyTrainTaskThumbnail>) findForList("CompanyTrainTaskMapper.selectThumbnailByTaskId",taskId);
    }

    @Override
    public List<CompanyTrainTaskCustomer> selectCustomerByTaskId(Long taskId) {
        return (List<CompanyTrainTaskCustomer>) findForList("CompanyTrainTaskMapper.selectCustomerByTaskId",taskId);
    }

    @Override
    public List<CompanyTrainTaskComment> selectCommentByTaskIdListPage(CompanyTrainTaskComment taskComment) {
        return (List<CompanyTrainTaskComment>) findForList("CompanyTrainTaskMapper.selectCommentByTaskIdListPage",taskComment);
    }

//    @Override
//    public List<CompanyTrainTaskLockMoney> selectLockMoney() {
//        return (List<CompanyTrainTaskLockMoney>) findForList("CompanyTrainTaskMapper.selectLockMoney");
//    }

    @Override
    public List<CompanyTrainTaskLockMoney> selectLockMoneyById(Map<String,Object> map) {
        return (List<CompanyTrainTaskLockMoney>) findForList("CompanyTrainTaskMapper.selectLockMoneyById",map);
    }

//    @Override
//    public List<CompanyTrainTaskLockMoney> selectLockMoneyByTaskId(Long taskId) {
//        return (List<CompanyTrainTaskLockMoney>) findForList("CompanyTrainTaskMapper.selectLockMoneyByTaskId",taskId);
//    }

    @Override
    public int selectCommentByTaskIdAndUserId(CompanyTrainTaskComment taskComment) {
        return (Integer) findForObject("CompanyTrainTaskMapper.selectCommentByTaskIdAndUserId",taskComment);
    }

    @Override
    public CompanyTrainTaskUser selectTaskUserByCidAndUserId(CompanyTrainTaskUser taskUser) {
        return (CompanyTrainTaskUser) findForObject("CompanyTrainTaskMapper.selectTaskUserByCidAndUserId",taskUser);
    }

    @Override
    public int selectTaskLikeById(CompanyTrainTaskLike trainTaskLike) {
        return (Integer) findForObject("CompanyTrainTaskMapper.selectTaskLikeById",trainTaskLike);
    }

    @Override
    public int insertTaskLike(CompanyTrainTaskLike trainTaskLike) {
        return (Integer) save("CompanyTrainTaskMapper.insertTaskLike",trainTaskLike);
    }

    @Override
    public int updateLockMoney(CompanyTrainTaskLockMoney taskLockMoney) {
        return (Integer) update("CompanyTrainTaskMapper.updateLockMoney",taskLockMoney);
    }

    @Override
    public double getTaskFrozenCharge(String customerId) {
        return (Double) findForObject("CompanyTrainTaskMapper.getTaskFrozenCharge",customerId);
    }

    @Override
    public int selectTaskUserAccount(String customerId) {
        return (Integer) findForObject("CompanyTrainTaskMapper.selectTaskUserAccount",customerId);
    }

    @Override
	public List<CompanyTrainTask> queryTask2ListPage(CompanyTrainTask companyTrainTask) {
		 return (List<CompanyTrainTask>) findForList("CompanyTrainTaskMapper.queryTask2ListPage",companyTrainTask);
	}
}
