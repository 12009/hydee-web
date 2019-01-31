package com.hydee.hdsec.controller.interfaceSec;

import com.hydee.hdsec.entity.CompanyTrainTask;
import com.hydee.hdsec.entity.CompanyTrainTaskLockMoney;
import com.hydee.hydee.service.CompanyTrainTaskService;
import com.hydee.hdsec.util.DateUtil;
import com.hydee.hdsec.util.LimitUtil;
import com.hydee.hdsec.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 * Created by King.Liu
 * 2016/9/9.
 */
public class TrainTaskShceduler {
    @Autowired
    public CompanyTrainTaskService companyTrainTaskService;

    /**
     * 更新任务状态
     */
    public void updateTaskStatus(){

        try {
            Date nowDate = new Date();
            List<CompanyTrainTask> trainTaskList = companyTrainTaskService.queryTaskByStatus(3);
            for (CompanyTrainTask trainTask : trainTaskList) {
                if(nowDate.getTime() > trainTask.getEndTime().getTime() || trainTask.getResidueMoney() == 0){
                    trainTask.setStatus(4);//设置为已结束
                    companyTrainTaskService.updateTaskById(trainTask);
                }

            }
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    /**
     * 解锁金额
     */
    public void deblockMoney(){
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("status",1);
            List<CompanyTrainTaskLockMoney> taskLockMoneys = companyTrainTaskService.selectLockMoneyById(params);
            Long lockTime = Long.parseLong(LimitUtil.TASKLOCKTIME) * 60 * 60 * 1000;
            if(null != taskLockMoneys && taskLockMoneys.size() > 0){
                for (CompanyTrainTaskLockMoney lockMoney : taskLockMoneys) {
                    Long time = DateUtil.getIntervalTimes(lockMoney.getCreateTime(),new Date());
                    if(time >= lockTime){
                        lockMoney.setStatus(0);
                        companyTrainTaskService.updateLockMoney(lockMoney);
                        CompanyTrainTask trainTask = companyTrainTaskService.selectTaskById(lockMoney.getTaskId());
                        trainTask.setResidueMoney(lockMoney.getLockMoney() + trainTask.getResidueMoney());
                        trainTask.setStatus(2);
                        companyTrainTaskService.updateTaskById(trainTask);
                    }else{
                        continue;
                    }
                }
            }

        } catch (Exception e) {
            Logger.error(e);
        }
    }
}
