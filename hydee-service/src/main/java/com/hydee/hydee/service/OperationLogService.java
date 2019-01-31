package com.hydee.hydee.service;

import com.hydee.hdsec.dao.OperationLogDao;
import com.hydee.hdsec.entity.IPAddress;
import com.hydee.hdsec.entity.OperationLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * 项目名称：hdsec
 * 类名称：OperationLogService
 * 类描述：
 * 创建人：zz
 * 创建时间：2014-12-30 下午4:41:22
 * 修改人：zz
 * 修改时间：2014-12-30 下午4:41:22
 * 修改备注：
 * @version Ver 1.1
 */
@Service
public class OperationLogService {

	@Resource(name = "operationLogDao")
	OperationLogDao operationLogDao;
	


	
	/**
	 * 添加日志
	 */
	public void addOperationLog(List<OperationLog> operationLogList){
		
		operationLogDao.addOperationLog(operationLogList);
	}
	

	public void insertIpAddr(IPAddress ipAddr) {
		operationLogDao.insertIpAddr(ipAddr);
	}
	
	public int countIpAddr(String ip) {
		return operationLogDao.countIpAddr(ip);
	}
	
}
