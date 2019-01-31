package com.hydee.hdsec.dao.impl;

import com.hydee.hdsec.dao.OperationLogDao;
import com.hydee.hdsec.dao.base.BaseDao;
import com.hydee.hdsec.entity.IPAddress;
import com.hydee.hdsec.entity.OperationLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
@Repository("operationLogDao")
public class OperationLogDaoImpl extends BaseDao implements OperationLogDao{
    @Override
    public void addOperationLog(List<OperationLog> operationLogList) {
        batchInsert("operationLogDao.addOperationLog",operationLogList);
    }

    @Override
    public void insertIpAddr(IPAddress ipAddr) {
        save("operationLogDao.insertIpAddr",ipAddr);
    }

    @Override
    public int countIpAddr(String ip) {
        return (Integer) findForObject("operationLogDao.countIpAddr",ip);
    }
}
